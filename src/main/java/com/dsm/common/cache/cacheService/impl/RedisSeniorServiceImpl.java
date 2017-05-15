package com.dsm.common.cache.cacheService.impl;

import com.dsm.common.cache.cacheDatabase.RedisDataSource;
import com.dsm.common.cache.cacheService.IRedisSeniorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/27
 *
 * @author : Lbwwz
 *         <p>
 *         redis中的高级存储操作，关于 list, set, hset(map) 和 sortedSet 的操作
 */
@Service("IRedisSeniorService")
public class RedisSeniorServiceImpl implements IRedisSeniorService {
    /**
     * 日志类
     */
    private static Logger logger = LoggerFactory.getLogger(RedisSeniorServiceImpl.class);
    @Resource
    private RedisDataSource redisDataSource;



    /********** list的相关操作 *********/

    /**
     * 添加 List
     *
     * @param key   key值
     * @param value 信息值
     * @return 操作状态
     */
    @Override
    public boolean addList(String key, String... value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.lpush(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("setList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 添加 List(只新增)
     *
     * @param key  key值
     * @param list 新增的列表元素
     * @return 操作状态
     */
    @Override
    public boolean addList(String key, List<String> list) {
        if (key == null || list == null || list.size() == 0) {
            return false;
        }
        String[] s = new String[list.size()];
        addList(key, list.toArray(s));
        return true;
    }

    /**
     * 添加 List（同时设置过期时间）
     *
     * @param key     key值
     * @param seconds 过期时间 单位s
     * @param value   信息值
     * @return 操作状态
     */
    @Override
    public boolean addList(String key, int seconds, String... value) {
        boolean result = addList(key, value);
        if (result) {
            long i = expire(key, seconds);
            return i == 1;
        }
        return false;
    }

    /**
     * 从list中删除value
     *
     * @param key   key值
     * @param count 要删除个数 从表头向表尾搜索（从左到右）。移除值等于value的元素，移除的数量为count
     * @param value 信息值
     * @return 操作状态
     */
    @Override
    public boolean removeListValue(String key, long count, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.lrem(key, count, value);
            return true;
        } catch (Exception ex) {
            logger.error("getList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 从list中删除value
     *
     * @param key 键
     * @param count 数量
     * @param values list中的值
     * @return 成功删除元素的数量
     */
    @Override
    public int removeListValue(String key, long count, List<String> values) {
        int result = 0;
        if (values != null && values.size() > 0) {
            //循环查找并删除
            for (String value : values) {
                if (removeListValue(key, count, value)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 从list中删除value 默认count 1
     *
     * @param key    设置的键
     * @param values 值list
     * @return 操作状态
     */
    @Override
    public int removeListValue(String key, List<String> values) {
        return removeListValue(key, 1, values);
    }

    /**
     * 截断一个List
     * <p>
     * LTRIM 的一个常见用法是和 LPUSH / RPUSH 一起使用。 例如：
     * <br> LPUSH mylist someelement
     * <br> LTRIM mylist 0 99
     * <p>这一对命令会将一个新的元素 push 进列表里，并保证该列表不会增长到超过100个元素。
     *
     * @param key   列表key
     * @param start 开始位置 从表头位置开始开始
     * @param end   结束位置 -1表示无结束位置
     * @return 状态码
     */
    @Override
    public boolean trimList(String key, long start, long end) {
        if (key == null || key.equals("")) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.ltrim(key, start, end).equals("-");
        } catch (Exception ex) {
            logger.error("LTRIM 出错[key=" + key + " start=" + start + " end="
                    + end + "]" + ex.getMessage(), ex);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 获取List(从表的左边到表的右边显示)
     *
     * @param key key值
     * @return list对象
     */
    @Override
    public List<String> getList(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.lrange(key, 0, -1);
        } catch (Exception ex) {
            logger.error("getList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 获取 List 指定位置元素（表头到表尾，从左到右）
     *
     * @param key   key值
     * @param start 起始位置 从左到右
     * @param end   结束位置 -1表示无结束位置
     * @return 截取获得的list信息
     */
    @Override
    public List<String> rangeList(String key, long start, long end) {
        if (key == null || key.equals("")) {
            return null;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.lrange(key, start, end);
        } catch (Exception ex) {
            logger.error("rangeList 出错[key=" + key + " start=" + start
                    + " end=" + end + "]" + ex.getMessage(), ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 检查List长度
     *
     * @param key key值
     * @return list长度
     */
    @Override
    public long countList(String key) {
        if (key == null) {
            return 0;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.llen(key);
        } catch (Exception ex) {
            logger.error("countList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }


    /********** set 的相关操作 *********/

    /**
     * 添加 Set
     *
     * @param key   设置的键
     * @param value 信息值
     * @return 操作状态
     */
    @Override
    public boolean addSet(String key, String... value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.sadd(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("setList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 添加 Set（同时设置过期时间）
     *
     * @param key     key值
     * @param seconds 过期时间 单位s
     * @param value   信息值
     * @return 操作状态
     */
    @Override
    public boolean addSet(String key, int seconds, String... value) {
        boolean result = addSet(key, value);
        if (result) {
            long i = expire(key, seconds);
            return i == 1;
        }
        return false;
    }

    /**
     * 检查Set长度
     *
     * @param key 键值
     * @return set长度
     */
    @Override
    public long countSet(String key) {
        if (key == null) {
            return 0;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.scard(key);
        } catch (Exception ex) {
            logger.error("countSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 判断某个值是否才set中
     *
     * @param key   key值
     * @param value 信息值
     * @return 判断值是否包含在set中
     */
    @Override
    public boolean containsInSet(String key, String value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.sismember(key, value);
        } catch (Exception ex) {
            logger.error("setList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获取Set
     *
     * @param key 设置的键
     * @return 获取set集合信息
     */
    @Override
    public Set<String> getSet(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.smembers(key);
        } catch (Exception ex) {
            logger.error("getList error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }


    /********** HashSet(map) 的相关操作 *********/

    /**
     * 设置 HashSet 对象
     *
     * @param key   键值
     * @param field 域名
     * @param value Json String or String value
     * @return 操作状态
     */
    @Override
    public boolean setHSet(String key, String field, String value) {
        if (value == null)
            return false;
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.hset(key, field, value) == 1;
        } catch (Exception ex) {
            logger.error("setHSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获得 HashSet 中某个域的值
     *
     * @param key   键
     * @param field 域名
     * @return Json String or String value
     */
    @Override
    public String getHSet(String key, String field) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.hget(key, field);
        } catch (Exception ex) {
            logger.error("getHSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 删除HashSet对象
     *
     * @param key   键值
     * @param field 域名
     * @return 删除的记录数
     */
    @Override
    public long delHSet(String key, String... field) {
        ShardedJedis shardedJedis = null;
        long count = 0;
        try {
            shardedJedis = redisDataSource.getResource();
            count = shardedJedis.hdel(key, field);
        } catch (Exception ex) {
            logger.error("delHSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return count;
    }

    /**
     * 判断某个 hashSet 中的某个域是否存在
     *
     * @param key   键值
     * @param field 域名
     * @return 是否存在
     */
    @Override
    public boolean existsHSet(String key, String field) {
        ShardedJedis shardedJedis = null;
        boolean isExist = false;
        try {
            shardedJedis = redisDataSource.getResource();
            isExist = shardedJedis.hexists(key, field);
        } catch (Exception ex) {
            logger.error("existsHSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return isExist;
    }

    /**
     * 全局扫描 hset
     *
     * @param key   键
     * @param match field匹配模式
     * @return 匹配获得的对象信息 {@link Map}
     */
    @Override
    public Map<String, String> scanHSet(String key, String match) {
        ShardedJedis shardedJedis = null;
        try {
            int cursor = 0;
            shardedJedis = redisDataSource.getResource();
            ScanParams scanParams = new ScanParams();
            scanParams.match(match);
            Jedis jedis = shardedJedis.getShard(key);
            ScanResult<Map.Entry<String, String>> scanResult;
            List<Map.Entry<String, String>> list = new ArrayList<>();
            do {
                scanResult = jedis.hscan(key, String.valueOf(cursor), scanParams);
                list.addAll(scanResult.getResult());
                cursor = Integer.parseInt(scanResult.getStringCursor());
            } while (cursor > 0);

            Map<String, String> map = new HashMap<>();
            for (Map.Entry<String, String> entry : list) {
                map.put(entry.getKey(), entry.getValue());
            }
            return map;
        } catch (Exception ex) {
            logger.error("scanHSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 查询该hashset中所有的域名
     *
     * @param key 键值
     * @return 该 key 中所有域的值。
     */
    @Override
    public List<String> hvals(String key) {
        ShardedJedis shardedJedis = null;
        List<String> retList = null;
        try {
            shardedJedis = redisDataSource.getResource();
            retList = shardedJedis.hvals(key);
        } catch (Exception ex) {
            logger.error("hvals error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return retList;
    }

    /**
     * 返回 key 指定的哈希集中所有字段的key值
     *
     * @param key 键
     * @return 包含哈希表中所有域名的列表。 当 key 不存在时，返回一个空列表。
     */
    @Override
    public Set<String> hkeys(String key) {
        ShardedJedis shardedJedis = null;
        Set<String> retList = null;
        try {
            shardedJedis = redisDataSource.getResource();
            retList = shardedJedis.hkeys(key);
        } catch (Exception ex) {
            logger.error("hkeys error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return retList;
    }

    /**
     * 返回 key 指定的哈希key值总数
     *
     * @param key 键
     * @return 该hashSet中域的个数
     */
    @Override
    public long lenHset(String key) {
        ShardedJedis shardedJedis = null;
        long retCount = 0;
        try {
            shardedJedis = redisDataSource.getResource();
            retCount = shardedJedis.hlen(key);
        } catch (Exception ex) {
            logger.error("hkeys error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return retCount;
    }

    /********* sorted set **********/

    /**
     * 设置排序集合
     *
     * @param key 键
     * @param score 排序号
     * @param value 值
     * @return 操作状态
     */
    @Override
    public boolean setSortedSet(String key, long score, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            shardedJedis.zadd(key, score, value);
            return true;
        } catch (Exception ex) {
            logger.error("setSortedSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获取排序集合（子集）
     * <p>指定分数区间内的集合
     *
     * @param key         键
     * @param startScore  开始 score
     * @param endScore    结束 score
     * @param orderByDesc 排序方式，true表示表尾到表头
     * @return 指定分数区间内，带有分数值(可选)的有序集成员的列表。
     */
    @Override
    public Set<String> getSoredSet(String key, long startScore, long endScore,
                                   boolean orderByDesc) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            if (orderByDesc) {
                return shardedJedis.zrevrangeByScore(key, endScore, startScore);
            } else {
                return shardedJedis.zrangeByScore(key, startScore, endScore);
            }
        } catch (Exception ex) {
            logger.error("getSoredSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 获得排序集合
     *
     * @param key         键
     * @param startRange  开始位置
     * @param endRange    结束位置
     * @param orderByDesc 顺序，true表示从表位到表头，false表示从表头到表尾
     * @return 根据开始位置和结束位置获取的排序集合
     */
    @Override
    public Set<String> getSoredSetByRange(String key, int startRange,
                                          int endRange, boolean orderByDesc) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            if (orderByDesc) {
                return shardedJedis.zrevrange(key, startRange, endRange);
            } else {
                return shardedJedis.zrange(key, startRange, endRange);
            }
        } catch (Exception ex) {
            logger.error("getSoredSetByRange error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 获取 排序集合(sorted set) 长度
     *
     * @param key 键
     * @param startScore 开始score
     * @param endScore 结束score
     * @return 长度
     */
    @Override
    public long countSoredSet(String key, long startScore, long endScore) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            Long count = shardedJedis.zcount(key, startScore, endScore);
            return count == null ? 0L : count;
        } catch (Exception ex) {
            logger.error("countSoredSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0L;
    }

    /**
     * 删除排序集合中的值
     *
     * @param key   键
     * @param value 值
     * @return 操作状态
     */
    @Override
    public boolean delSortedSet(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            long count = shardedJedis.zrem(key, value);
            return count > 0;
        } catch (Exception ex) {
            logger.error("delSortedSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }


    /**
     * 获得排序集合打分
     *
     * @param key    键
     * @param member 集合的元素值
     * @return score值
     */
    @Override
    public Double getScore(String key, String member) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisDataSource.getResource();
            return shardedJedis.zscore(key, member);
        } catch (Exception ex) {
            logger.error("getSoredSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 释放jedis
     *
     * @param shardedJedis 需要释放的jedis对象
     */
    private void returnResource(ShardedJedis shardedJedis) {
        try {
            redisDataSource.closeJedis(shardedJedis);
        } catch (Exception e) {
            logger.error("returnResource error.", e);
        }
    }


    /**
     * 设置redis缓存过期
     */
    private long expire(String key, int seconds) {
        if (key == null || key.equals("")) {
            return 0;
        }

        ShardedJedis shardedJedis = null;
        try {
            return (shardedJedis = redisDataSource.getResource()).expire(key, seconds);
        } catch (Exception ex) {
            logger.error("EXPIRE error[key=" + key + " seconds=" + seconds
                    + "]" + ex.getMessage(), ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

}
