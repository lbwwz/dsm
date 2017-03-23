package com.dsm.common.cache.cacheDatabase;

import redis.clients.jedis.ShardedJedis;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/27
 *
 * @author : Lbwwz
 */
public interface RedisDataSource {

    /**
     * 获取jedis对象
     * @return {@link ShardedJedis}
     */
    ShardedJedis getResource();


    /**
     * 释放jedis
     * @param shardJedis 需要关闭（释放）的jedis对象
     */
    void closeJedis(ShardedJedis shardJedis);

}
