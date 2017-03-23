package com.dsm.common.cache.cacheService.impl;

import com.dsm.common.cache.cacheDatabase.RedisDataSource;
import com.dsm.common.cache.cacheService.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/27
 *
 * @author : Lbwwz
 *         <p>
 *         redis的相关操作实现（基本操作）
 */
@Service("iRedisService")
public class RedisServiceImpl implements IRedisService{

    /**
     * 日志类
     */
    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Resource
    protected RedisDataSource redisDataSource;


    /*******************常用操作***************/

    /**
     * 设置一个key的过期时间（单位：秒）
     *
     * @param key     key值
     * @param seconds 多少秒后过期
     * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
     */
    @Override
    public long expire(String key, int seconds) {
        if (key == null || key.equals("")) {
            return 0;
        }

        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.expire(key, seconds);
        } catch (Exception ex) {
            logger.error("EXPIRE error[key=" + key + " seconds=" + seconds
                    + "]" + ex.getMessage(), ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 设置一个key在某个时间点过期
     *
     * @param key           key值
     * @param unixTimestamp unix时间戳，从1970-01-01 00:00:00开始到现在的秒数
     * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
     */
    @Override
    public long expireAt(String key, int unixTimestamp) {
        if (key == null || key.equals("")) {
            return 0;
        }

        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.expireAt(key, unixTimestamp);
        } catch (Exception ex) {
            logger.error("EXPIRE error[key=" + key + " unixTimestamp="
                    + unixTimestamp + "]" + ex.getMessage(), ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 设置时效缓存
     *
     * @param key    键
     * @param value  信息值
     * @param second 缓存时间
     * @return 操作状态值
     */
    @Override
    public boolean set(String key, String value, int second) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.setex(key, second, value);
            return true;
        } catch (Exception ex) {
            logger.error("set error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }


    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 信息值
     * @return 操作状态值
     */
    @Override
    public boolean set(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.set(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("set error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param key          键
     * @param defaultValue 默认值，当查询结果为空时返回此值
     * @return 查询的结果
     */
    @Override
    public String get(String key, String defaultValue) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.get(key) == null ? defaultValue : shardedJedis
                    .get(key);
        } catch (Exception ex) {
            logger.error("get error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return defaultValue;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return 操作的状态值
     */
    @Override
    public boolean del(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.del(key);
            return true;
        } catch (Exception ex) {
            logger.error("del error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 将 key 中储存的数字值增一。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * <i>本操作的值限制在 64 位(bit)有符号数字表示之内。</i>
     *
     * @param key 键
     * @return 加一后的值
     */
    @Override
    public long incr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.incr(key);
        } catch (Exception ex) {
            logger.error("incr error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 减小存储在由指定的值的key的数量。
     * 如果该键不存在时，它被设置为0在执行操作之前。
     * 如果键包含了错误类型的值或包含不能被表示为整数，字符串，则返回错误。
     *
     * @param key 键
     * @return 减一后的值
     */
    @Override
    public long decr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.decr(key);
        } catch (Exception ex) {
            logger.error("incr error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 释放jedis
     *
     * @param shardedJedis 需要释放的jedis对象
     */
    protected void returnResource(ShardedJedis shardedJedis) {
        try {
            redisDataSource.closeJedis(shardedJedis);
        } catch (Exception e) {
            logger.error("returnResource error.", e);
        }
    }

}
