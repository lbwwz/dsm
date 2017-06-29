package com.dsm.common.cache.cacheDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/27
 *
 * @author : Lbwwz
 */
@Component
@Repository("RedisDatasource")
public class RedisDatasourceImpl implements RedisDataSource {

    private static Logger logger = LoggerFactory.getLogger(RedisDataSource.class);
    @Resource
    private ShardedJedisPool shardedJedisPool;

//    private static Logger logger = LoggerFactory.getLogger(RedisDatasourceImpl.class);

    @Value("${redis.retry.num}")
    private  String RETRY_NUM;

    public void setRetryNum(String retryNum) {
        RETRY_NUM = retryNum;
    }

    @Override
    public ShardedJedis getResource() {
        ShardedJedis shardJedis = null;

        int count = 0;
        do {
            try {
                shardJedis = shardedJedisPool.getResource();
            } catch (Exception e) {
                logger.error("RedisDataSource ERROR[getRedis error!]", e);
            }
        }
        while (shardJedis == null && count++ < Integer.parseInt(RETRY_NUM));
        return shardJedis;
    }

    @Override
    public void closeJedis(ShardedJedis shardedJedis) {
        if (shardedJedis != null) {
            shardedJedis.close();
        }
    }
}
