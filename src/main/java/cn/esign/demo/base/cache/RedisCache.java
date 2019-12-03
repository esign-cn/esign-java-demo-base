package cn.esign.demo.base.cache;

/**
 * @author zhexiu
 * @since 2019/7/16 上午9:24
 */
public class RedisCache<K, V> implements Cache<K, V> {

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }
}
