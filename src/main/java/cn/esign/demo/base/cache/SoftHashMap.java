package cn.esign.demo.base.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考自 Apache Shiro SoftHashMap
 * <p/>
 * <b>key1</b>:SoftReference: 被软引用关联的对象，只有在内存不够的情况下才会被回收。
 * <p/>
 * <b>key2</b>:如果构造在弱|软引用实例时，把ReferenceQueue当做构造函数参数传给实例的话，在弱|软引用被GC清除时，会把被清除的实例的引用放到ReferenceQueue里（SoftReference 指向的对象持有强引用和软引用，不会被回收，因此不会加入到queue中）
 * <p/>
 * <b>key3</b>:SoftValue 继承自 SoftReference， SoftValue<V, K> extends SoftReference<V>，只传递了V，故Key不会被自动清除<br/>
 * 所以通过定义processQueue ，获取到被清除的SoftValue,再通过SoftValue的Key，从Map里手动清除掉Key， map.remove(sv.key)<br/>
 * 所以在get|put|contains 方法之前调用这个从map里清除失效缓存的方法，这样就不会出现Key存在，但Value被清除掉的情况
 * <p/>
 * <b>key4</b>:Map 长度并不会无限扩展，当大于RETENTION_SIZE时，根据FIFO，poll 掉
 * <p/>
 *
 * @since 1.0
 */
public class SoftHashMap<K, V> implements Map<K, V> {

    /**
     * The default value of the RETENTION_SIZE attribute, equal to 100.
     */
    private static final int DEFAULT_RETENTION_SIZE = 100;

    /**
     * The internal HashMap that will hold the SoftReference.
     */
    private final Map<K, SoftValue<V, K>> map;

    /**
     * The number of strong references to hold internally, that is, the number of instances to prevent
     * from being garbage collected automatically (unlike other soft references).
     */
    private final int RETENTION_SIZE;

    /**
     * The FIFO list of strong references (not to be garbage collected), order of last access.
     */
    private final Queue<V> strongReferences; //guarded by 'strongReferencesLock'
    private final ReentrantLock strongReferencesLock;

    /**
     * Reference queue for cleared SoftReference objects.
     */
    private final ReferenceQueue<? super V> queue;

    /**
     * Creates a new SoftHashMap build a default retention size size of
     * {@link #DEFAULT_RETENTION_SIZE DEFAULT_RETENTION_SIZE} (100 entries).
     *
     * @see #SoftHashMap(int)
     */
    public SoftHashMap() {
        this(DEFAULT_RETENTION_SIZE);
    }

    /**
     * Creates a new SoftHashMap build the specified retention size.
     * <p/>
     * The retention size (n) is the total number of most recent entries in the map that will be strongly referenced
     * (ie 'retained') to prevent them from being eagerly garbage collected.  That is, the point of a SoftHashMap is to
     * allow the garbage collector to remove as many entries from this map as it desires, but there will always be (n)
     * elements retained after a GC due to the strong references.
     * <p/>
     * Note that in a highly concurrent environments the exact total number of strong references may differ slightly
     * than the actual <getCode>retentionSize</getCode> value.  This number is intended to be a best-effort retention low
     * water mark.
     *
     * @param retentionSize the total number of most recent entries in the map that will be strongly referenced
     *                      (retained), preventing them from being eagerly garbage collected by the JVM.
     */
    @SuppressWarnings({"unchecked"})
    public SoftHashMap(int retentionSize) {
        super();
        RETENTION_SIZE = Math.max(0, retentionSize);
        queue = new ReferenceQueue<V>();
        strongReferencesLock = new ReentrantLock();
        map = new ConcurrentHashMap<K, SoftValue<V, K>>();
        strongReferences = new ConcurrentLinkedQueue<V>();
    }

    /**
     * Creates a {@code SoftHashMap} backed by the specified {@code source}, build a default retention
     * size of {@link #DEFAULT_RETENTION_SIZE DEFAULT_RETENTION_SIZE} (100 entries).
     *
     * @param source the backing map to populate this {@code SoftHashMap}
     * @see #SoftHashMap(Map, int)
     */
    public SoftHashMap(Map<K, V> source) {
        this(DEFAULT_RETENTION_SIZE);
        putAll(source);
    }

    /**
     * Creates a {@code SoftHashMap} backed by the specified {@code source}, build the specified retention size.
     * <p/>
     * The retention size (n) is the total number of most recent entries in the map that will be strongly referenced
     * (ie 'retained') to prevent them from being eagerly garbage collected.  That is, the point of a SoftHashMap is to
     * allow the garbage collector to remove as many entries from this map as it desires, but there will always be (n)
     * elements retained after a GC due to the strong references.
     * <p/>
     * Note that in a highly concurrent environments the exact total number of strong references may differ slightly
     * than the actual <getCode>retentionSize</getCode> value.  This number is intended to be a best-effort retention low
     * water mark.
     *
     * @param source        the backing map to populate this {@code SoftHashMap}
     * @param retentionSize the total number of most recent entries in the map that will be strongly referenced
     *                      (retained), preventing them from being eagerly garbage collected by the JVM.
     */
    public SoftHashMap(Map<K, V> source, int retentionSize) {
        this(retentionSize);
        putAll(source);
    }

    @Override
    public V get(Object key) {
        processQueue();

        V result = null;
        SoftValue<V, K> value = map.get(key);

        if (value != null) {
            //unwrap the 'real' value from the SoftReference
            result = value.get();
            if (result == null) {
                //The wrapped value was garbage collected, so remove this entry from the backing map:
                //noinspection SuspiciousMethodCalls
                map.remove(key);
            } else {
                //Add this value to the beginning of the strong reference queue (FIFO).
                addToStrongReferences(result);
            }
        }
        return result;
    }

    private void addToStrongReferences(V result) {
        strongReferencesLock.lock();
        try {
            strongReferences.add(result);
            trimStrongReferencesIfNecessary();
        } finally {
            strongReferencesLock.unlock();
        }

    }

    //Guarded by the strongReferencesLock in the addToStrongReferences method

    private void trimStrongReferencesIfNecessary() {
        //trim the strong ref queue if necessary:
        while (strongReferences.size() > RETENTION_SIZE) {
            strongReferences.poll();
        }
    }

    /**
     * Traverses the ReferenceQueue and removes garbage-collected SoftValue objects from the backing map
     * by looking them up using the SoftValue.key data member.
     */
    private void processQueue() {
        SoftValue sv;
        while ((sv = (SoftValue) queue.poll()) != null) {
            //noinspection SuspiciousMethodCalls
            map.remove(sv.key); // we can access private data!
        }
    }

    @Override
    public boolean isEmpty() {
        processQueue();
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        processQueue();
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        processQueue();
        Collection values = values();
        return values != null && values.contains(value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null || m.isEmpty()) {
            processQueue();
            return;
        }
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Set<K> keySet() {
        processQueue();
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        processQueue();
        Collection<K> keys = map.keySet();
        if (keys.isEmpty()) {
            //noinspection unchecked
            return Collections.EMPTY_SET;
        }
        Collection<V> values = new ArrayList<V>(keys.size());
        for (K key : keys) {
            V v = get(key);
            if (v != null) {
                values.add(v);
            }
        }
        return values;
    }

    /**
     * Creates a new entry, but wraps the value in a SoftValue instance to enable auto garbage collection.
     */
    @Override
    public V put(K key, V value) {
        processQueue(); // throw out garbage collected values first
        SoftValue<V, K> sv = new SoftValue<V, K>(value, key, queue);
        SoftValue<V, K> previous = map.put(key, sv);
        addToStrongReferences(value);
        return previous != null ? previous.get() : null;
    }

    @Override
    public V remove(Object key) {
        processQueue(); // throw out garbage collected values first
        SoftValue<V, K> raw = map.remove(key);
        return raw != null ? raw.get() : null;
    }

    @Override
    public void clear() {
        strongReferencesLock.lock();
        try {
            strongReferences.clear();
        } finally {
            strongReferencesLock.unlock();
        }
        processQueue(); // throw out garbage collected values
        map.clear();
    }

    @Override
    public int size() {
        processQueue(); // throw out garbage collected values first
        return map.size();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        processQueue(); // throw out garbage collected values first
        Collection<K> keys = map.keySet();
        if (keys.isEmpty()) {
            //noinspection unchecked
            return Collections.EMPTY_SET;
        }

        Map<K, V> kvPairs = new HashMap<K, V>(keys.size());
        for (K key : keys) {
            V v = get(key);
            if (v != null) {
                kvPairs.put(key, v);
            }
        }
        return kvPairs.entrySet();
    }

    /**
     * We define our own subclass of SoftReference which contains
     * not only the value but also the key to make it easier to find
     * the entry in the HashMap after it's been garbage collected.
     */
    private static class SoftValue<V, K> extends SoftReference<V> {

        private final K key;

        /**
         * Constructs a new instance, wrapping the value, key, and queue, as
         * required by the superclass.
         *
         * @param value the map value
         * @param key   the map key
         * @param queue the soft reference queue to poll to determine if the entry had been reaped by the GC.
         */
        private SoftValue(V value, K key, ReferenceQueue<? super V> queue) {
            super(value, queue);
            this.key = key;
        }

    }
}