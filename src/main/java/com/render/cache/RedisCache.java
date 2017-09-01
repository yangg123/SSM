package com.render.cache;

import org.apache.ibatis.cache.CacheException;
/**
 * Redis-shiro-Cache
 * @author wuzy
 * 2016年12月18日 下午9:42:42
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RedisCache<K,V> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisManager cache;
    public RedisManager getCache() {
        return cache;
    }
    public void setCache(RedisManager cache) {
        this.cache = cache;
    }

    /**
     * 通过JedisManager 获取 RedisCache
     * @param cache
     */
    public RedisCache(RedisManager cache){
        if(cache==null){
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache=cache;
    }
    /**
     * Constructs a cache instance with the specified
     * Redis manager and using a custom key prefix.
     * @param cache The cache manager instance
     * @param prefix The Redis key prefix
     */
    public RedisCache(RedisManager cache, String prefix){
        this(cache);
    }
    /**
     * 获取redis中的数据
     * @param key
     * @return
     */
    private byte[] getByteKey(K key){
        if(key instanceof String){
            String preKey=key+"";
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize(key);
        }
    }
    public V get(K key) throws CacheException{
        logger.debug("通过key从redis中获取数据。key【"+key+"】");
        try {
            if(key == null){
                return null;
            }else{
                byte[] rawValue=cache.get(getByteKey(key));
                @SuppressWarnings("unchecked")
                V value =(V)SerializeUtils.deserialize(rawValue);
                return value;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }

    }
    /**
     * put
     */
    public V put(K key,V value)throws CacheException{
        logger.debug("redis put方法》》key:"+key);
        try {
            cache.set(getByteKey(key), SerializeUtils.serialize(value));
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * remove
     */
    public V remove(K key)throws CacheException{
        logger.debug("redis remove方法》》key:"+key);
        try {
            V previous = get(key);
            cache.del(getByteKey(key));
            return previous;
        } catch (Throwable e) {
            throw new CacheException(e);
        }
    }
    /**
     * clear
     */
    public void clear() throws CacheException{
        logger.debug("redis clear方法》》");
        try {
            cache.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * size
     */
    public int size() {
        try {
            Long longSize = new Long(cache.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
