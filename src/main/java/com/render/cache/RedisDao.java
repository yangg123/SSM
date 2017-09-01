package com.render.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ssm.model.Seckill;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;


/**
 * Created by codingBoy on 17/2/17.
 */
public class RedisDao {

//    private final JedisPool jedisPool;
//
//    public RedisDao(String ip, int port) {
//        jedisPool = new JedisPool(ip, port);
//    }

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisManager redisManager;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);


    public Seckill getSeckill(long seckillId) {

//        Jedis jedis = jedisPool.getResource();
        String key = "seckill:" + seckillId;
        //并没有实现哪部序列化操作
        //采用自定义序列化
        //protostuff: pojo.

        redisManager.init();
        byte[] bytes = redisManager.get(key.getBytes());
//                byte[] bytes = jedis.get(key.getBytes());
        //缓存重获取到
        if (bytes != null) {
            Seckill seckill=schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
            //seckill被反序列化
            return seckill;
        }

        return null;
    }

    public <T> T putSeckill(Seckill seckill) {

//        Jedis jedis = jedisPool.getResource();
        String key = "seckill:" + seckill.getSeckillId();
        byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        //超时缓存
        int timeout = 60 * 60;//1小时
        redisManager.init();
        byte[] bytes1 = redisManager.set(key.getBytes(),bytes,timeout);
        logger.info("-------------bytes1"+bytes1);

        //return bytes1;
//                return jedis.setex(key.getBytes(),timeout,bytes);

        return null;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }
}
