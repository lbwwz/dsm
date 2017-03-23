package com.dsm.common.cache.cacheDatabase;

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
@Repository("RedisDatasource")
public class RedisDatasourceImpl implements RedisDataSource {

    @Resource
    private ShardedJedisPool shardedJedisPool;

//    private static Logger logger = LoggerFactory.getLogger(RedisDatasourceImpl.class);


    @Override
    public ShardedJedis getResource() {
        ShardedJedis shardJedis;
        try {

            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
//            logger.error("RedisDataSource ERROR[getRedis error!]", e);
        }
        return null;
    }

    @Override
    public void closeJedis(ShardedJedis shardedJedis) {
        if (shardedJedis != null) {
            shardedJedis.close();
        }
    }
}
