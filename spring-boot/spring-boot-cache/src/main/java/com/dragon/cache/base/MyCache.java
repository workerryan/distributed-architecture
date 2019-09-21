package com.dragon.cache.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义的一个单JVM的Cache
 * 要实现过期时间，那么需要启动2个线程，主线程进行数据的操作，另外一个线程来定时执行删除
 * @author wanglei
 */
public class MyCache<K, V> {
    private Map<K, V> cacheMap = new ConcurrentHashMap<>();


    public void put(K k, V v){
        cacheMap.put(k, v);
    }

    public V get(K k){
        return cacheMap.get(k);
    }

    public void remove(K k){
        cacheMap.remove(k);
    }
}
