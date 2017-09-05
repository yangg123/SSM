package com.render.cache;

import org.apache.ibatis.cache.CacheException;
/**
 * Redis-shiro-Cache
 * @author wuzy
 * 2016年12月18日 下午9:42:42
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RedisCache {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisManager redisManager;
    public RedisManager getCache() {
        return redisManager;
    }
    public void setCache(RedisManager cache) {
        this.redisManager = cache;
    }

    /**
     * 通过JedisManager 获取 RedisCache
     * @param redisManager
     */
    public RedisCache(RedisManager redisManager){
        if(redisManager==null){
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.redisManager=redisManager;
    }
    /**
     * Constructs a cache instance with the specified
     * Redis manager and using a custom key prefix.
     * @param cache The cache manager instance
     * @param prefix The Redis key prefix
     */
    /**
     * 获取redis中的数据
     * @param key
     * @return
     */
    private byte[] getByteKey(Object key){
        if(key instanceof String){
            String preKey=key+"";
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize(key);
        }
    }
    public <T> T get(Object key) throws CacheException {
        logger.debug("通过key从redis中获取数据。key【"+key+"】");
        try {
            if(key == null){
                return null;
            }else{
                byte[] rawValue=redisManager.get(getByteKey(key));
                @SuppressWarnings("unchecked")
                T value =(T)SerializeUtils.deserialize(rawValue);
                return value;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * put
     */
    public String set(Object key, Object value)throws CacheException {
        logger.info("redis put方法》》key:"+key);
        return redisManager.set(getByteKey(key), SerializeUtils.serialize(value));
    }

    /**
     * remove
     */
    public void remove(Object key)throws CacheException {
        logger.debug("redis remove方法》》key:"+key);
        try {
            redisManager.del(getByteKey(key));
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
            redisManager.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * size
     */
    public int size() {
        try {
            Long longSize = new Long(redisManager.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
