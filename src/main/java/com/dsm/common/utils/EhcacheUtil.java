package com.dsm.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * Created by Lbwwz on 2016/8/22.
 *
 * ehcache的缓存工具类 缓存时间为一小时
 */
@SuppressWarnings("unused")
public final class EhcacheUtil {
    private static final CacheManager cacheManager = CacheManager.getInstance();
    /**
     * 创建ehcache缓存，创建之后的有效期是1小时
     */
    private static Cache cache = new Cache(new CacheConfiguration("systemCache", 5000)
            .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO).timeoutMillis(300)
            .timeToLiveSeconds( 60 * 60));
    static {
        cacheManager.addCache(cache);
    }
    public static void putItem(String key, Object item) {
        if (cache.get(key) != null) {
            cache.remove(key);
        }
        Element element = new Element(key, item);
        cache.put(element);
    }
    @SuppressWarnings("unused")
    public static void removeItem(String key) {
        cache.remove(key);
    }
    @SuppressWarnings("unused")
    public static void updateItem(String key, Object value) {
        putItem(key, value);
    }
    @SuppressWarnings("unused")
    public static Object getItem(String key) {
        Element element=  cache.get(key);
        if(null!=element)
        {
            return element.getObjectValue();
        }
        return null;
    }
}