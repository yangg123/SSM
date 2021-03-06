package com.render.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Set;

/**
 * redis 连接属性实体 及初始化 ;redis相关操作。
 * Created by Administrator on 2017/8/31.
 */
public class RedisManager {

    private static Logger logger= LoggerFactory.getLogger(RedisManager.class);

    /** 连接Ip */
    private String host;
    /** 端口号 */
    private int port;
    /** 过期时间 （0：永不过期） */
    private int expire=0;
    /** 连接redis server时间，In milliseconds  */
    private int timeout;
    /** 密码 */
    private String password;

    /** 客户端连接 */
    private static JedisPool jedisPool;

    /**
     * redis连接初始化
     */
    public void init(){

        if(jedisPool== null){

            if(password!=null && !"".equals(password)){
                jedisPool=new JedisPool(new JedisPoolConfig(),host,port,timeout,password,10);
            } else if(timeout!=0){
                jedisPool=new JedisPool(new JedisPoolConfig(),host,port,timeout);
            } else {
                jedisPool=new JedisPool(new JedisPoolConfig(),host,port);
            }
        }
    }

    /**
     * 从redis中get
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {

        init();
        byte[]  value = null;
        Jedis jedis=jedisPool.getResource();
        try {
            value=jedis.get(key);
        } catch (Exception e) {
            logger.info("从redis获得数据失败》》",e);
        }
        return value;
    }
    /**
     * redis set （永不过期）
     * @param key
     * @param value
     * @return
     */
    public String set(byte[] key,byte[] value) {

        init();
        Jedis jedis = jedisPool.getResource();
        try {
            if(this.expire !=0){//设置过期时间
                jedis.expire(key, this.expire);
            }
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.info("redis set 数据失败》》",e);
        }
        return null;
    }
    /**
     * redis set
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String set(byte[] key,byte[] value, int expire) {

        Jedis jedis = jedisPool.getResource();
        try {
            if(expire !=0){ //设置过期时间
                jedis.expire(key, expire);
            }
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.info("redis set 数据失败》》",e);
        }
        return null;
    }
    /**
     * redis del
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } catch (Exception e) {
            logger.info("redis del 数据失败》》",e);
        } finally {
            jedisPool.close();
        }
    }

    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            jedis.close();
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try{
            dbSize = jedis.dbSize();
        } finally {
            jedis.close();
        }
        return dbSize;
    }

    /**
     * keys
     * @param
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            jedisPool.close();
        }
        return keys;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
