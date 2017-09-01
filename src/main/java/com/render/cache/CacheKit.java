package com.render.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/31.
 */
public class CacheKit {

    private static final String path = "/ehcache.xml";
    private static final String path1 = "src/main/resources/ehcache.xml";

    private URL url;

    private static CacheManager manager;

    private static CacheKit ehCache;

    private CacheKit(String path) {

        url = getClass().getResource(path);
        manager = CacheManager.create(url);

       // manager = new CacheManager(path1);

       // InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
       // manager = new CacheManager(is);
    }

    public static CacheManager getCacheManager() {
        return manager;
    }

    public static CacheKit getInstance() {
        if (ehCache== null) {
            ehCache= new CacheKit(path);
        }
        return ehCache;
    }

    public void put(String cacheName, Object key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public <T> T get(String cacheName, Object key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : (T)element.getObjectValue();
    }

    public Cache get(String cacheName) {
        return manager.getCache(cacheName);
    }

    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }
}
