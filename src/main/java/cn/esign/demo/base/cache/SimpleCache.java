package cn.esign.demo.base.cache;

import java.util.Map;

/**
 * @author zhexiu
 * @since 2019/7/16 上午9:22
 */
public class SimpleCache<K, V> implements Cache<K, V> {

    private Map<K, V> cache = new SoftHashMap<>();

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V put(K key, V value) {
        return cache.put(key, value);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public V remove(K key) {
        return cache.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

}
