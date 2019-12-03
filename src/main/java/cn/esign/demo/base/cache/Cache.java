package cn.esign.demo.base.cache;

import cn.esign.demo.base.extension.SPI;

/**
 * 缓存接口
 *
 * @author zhexiu
 * @since 2019/7/16 上午9:21
 */
@SPI
public interface Cache<K, V> {

    /**
     * 获取
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 添加
     *
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    /**
     * 清除全部
     */
    void clear();

    /**
     * 移除
     *
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    boolean containsKey(K key);
}

