package com.dsm.common.cache.cacheService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/27
 *
 * @author : Lbwwz
 *         <p>
 *         缓存中常用数据结构，关于 list, set, hset(map) 和 sortedSet 的操作接口
 */
public interface IRedisSeniorService{

    /********** list的相关操作 *********/

    /**
     * 添加 List
     *
     * @param key   key值
     * @param value 信息值
     * @return 操作状态
     */
    boolean addList(String key, String... value);

    /**
     * 添加 List(只新增)
     *
     * @param key  key值
     * @param list 新增的列表元素
     * @return 操作状态
     */
    boolean addList(String key, List<String> list);

    /**
     * 添加 List（同时设置过期时间）
     *
     * @param key     key值
     * @param seconds 过期时间 单位s
     * @param value   信息值
     * @return 操作状态
     */
    boolean addList(String key, int seconds, String... value);

    /**
     * 从list中删除value
     *
     * @param key   key值
     * @param count 要删除个数 从表头向表尾搜索（从左到右）。移除值等于value的元素，移除的数量为count
     * @param value 信息值
     * @return 操作状态
     */
    boolean removeListValue(String key, long count, String value);

    /**
     * 从list中删除value
     *
     * @param key    键
     * @param count  删除数量
     * @param values 值list
     * @return 成功删除元素的数量
     */
    int removeListValue(String key, long count, List<String> values);

    /**
     * 从list中删除value 默认count 1
     *
     * @param key    设置的键
     * @param values 值list
     * @return 操作状态
     */
    int removeListValue(String key, List<String> values);

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
    boolean trimList(String key, long start, long end);

    /**
     * 获取List(从表的左边到表的右边显示)
     *
     * @param key key值
     * @return list对象
     */
    List<String> getList(String key);

    /**
     * 获取 List 指定位置元素（表头到表尾，从左到右）
     *
     * @param key   key值
     * @param start 起始位置 从左到右
     * @param end   结束位置 -1表示无结束位置
     * @return 截取获得的list信息
     */
    List<String> rangeList(String key, long start, long end);

    /**
     * 检查List长度
     *
     * @param key key值
     * @return list长度
     */
    long countList(String key);


    /********** set 的相关操作 *********/

    /**
     * 添加 Set
     *
     * @param key   设置的键
     * @param value 信息值
     * @return 操作状态
     */
    boolean addSet(String key, String... value);

    /**
     * 添加 Set（同时设置过期时间）
     *
     * @param key     key值
     * @param seconds 过期时间 单位s
     * @param value   信息值
     * @return 操作状态
     */
    boolean addSet(String key, int seconds, String... value);

    /**
     * 检查Set长度
     *
     * @param key 键值
     * @return set长度
     */
    long countSet(String key);

    /**
     * 判断某个值是否才set中
     *
     * @param key   key值
     * @param value 信息值
     * @return 判断值是否包含在set中
     */
    boolean containsInSet(String key, String value);

    /**
     * 获取Set
     *
     * @param key 设置的键
     * @return 获取set集合信息
     */
    Set<String> getSet(String key);


    /********** HashSet(map) 的相关操作 *********/

    /**
     * 设置 HashSet 对象
     *
     * @param key   键值
     * @param field 域名
     * @param value Json String or String value
     * @return 操作状态
     */
    boolean setHSet(String key, String field, String value);

    /**
     * 获得 HashSet 中某个域的值
     *
     * @param key   键
     * @param field 域名
     * @return Json String or String value
     */
    String getHSet(String key, String field);

    /**
     * 删除HashSet对象
     *
     * @param key   键值
     * @param field 域名
     * @return 删除的记录数
     */
    long delHSet(String key, String... field);

    /**
     * 判断某个 hashSet 中的某个域是否存在
     *
     * @param key   键值
     * @param field 域名
     * @return 是否存在
     */
    boolean existsHSet(String key, String field);

    /**
     * 全局扫描 hset
     *
     * @param key   键
     * @param match field匹配模式
     * @return 匹配获得的对象信息 {@link Map}
     */
    Map<String, String> scanHSet(String key, String match);

    /**
     * 查询该hashset中所有的域名
     *
     * @param key 键值
     * @return 该 key 中所有域的值。
     */
    List<String> hvals(String key);

    /**
     * 返回 key 指定的哈希集中所有字段的key值
     *
     * @param key 键
     * @return 包含哈希表中所有域名的列表。 当 key 不存在时，返回一个空列表。
     */

    Set<String> hkeys(String key);

    /**
     * 返回 key 指定的哈希key值总数
     *
     * @param key 键
     * @return 该hashSet中域的个数
     */
    long lenHset(String key);

    /********* sorted set **********/

    /**
     * 设置排序集合
     *
     * @param key   键
     * @param score 排序号
     * @param value 值
     * @return 操作状态
     */
    boolean setSortedSet(String key, long score, String value);


    /**
     * 获取排序集合（子集）
     *
     * @param key         键
     * @param startScore  开始序号
     * @param endScore    结束序号
     * @param orderByDesc 排序方式
     * @return 截取的排序集合
     */
    Set<String> getSoredSet(String key, long startScore, long endScore,
                            boolean orderByDesc);

    /**
     * 获得排序集合
     *
     * @param key         键
     * @param startRange  开始位置
     * @param endRange    结束位置
     * @param orderByDesc 顺序，true表示从表位到表头，false表示从表头到表尾
     * @return 根据开始位置和结束位置获取的排序集合
     */
    Set<String> getSoredSetByRange(String key, int startRange,
                                   int endRange, boolean orderByDesc);


    /**
     * 获取 排序集合(sorted set) 长度
     *
     * @param key        键
     * @param startScore 开始序号
     * @param endScore   结束序号
     * @return 两序号之间元素个数
     */
    long countSoredSet(String key, long startScore, long endScore);

    /**
     * 删除排序集合
     *
     * @param key   键
     * @param value 删除值
     * @return 状态值
     */
    boolean delSortedSet(String key, String value);


    /**
     * 获得排序集合的 score
     *
     * @param key    键
     * @param member 集合的元素值
     * @return score值
     */
    Double getScore(String key, String member);

}
