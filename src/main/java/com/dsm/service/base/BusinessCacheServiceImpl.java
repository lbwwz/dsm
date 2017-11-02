package com.dsm.service.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheDatabase.RedisDataSource;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.dao.IProductSkuDao;
import com.dsm.model.product.ProductSkuItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/16
 *
 * @author : Lbwwz
 */
@Service("IBusinessCacheService")
public class BusinessCacheServiceImpl implements IBusinessCacheService {

    @Autowired
    private IRedisService redisService;

    @Resource
    private IProductSkuDao productSkuDao;

    //redis中一个hashSet中默认维护的key的数量
    private int cache_hSet_Size = 10000;


    //用于key中时间戳和数据信息的分隔符
    private String expire_time_separate = DsmConcepts.EXPIRE_TIME_SEPARATE;

    @Override
    public ProductSkuItem getProductSkuItemFromCache( boolean mustFromDB, Long skuId) {
        if (skuId == null) {
            return null;
        }
        ProductSkuItem productSkuItem = null;
        /**
         * 改用哈希结构减少redis中要维护的key的数量（每个哈希存放最多{@link BusinessCacheServiceImpl#cache_hSet_Size}数量的键值信息）
         */
        String skuCacheKey = "productSku_" + skuId / cache_hSet_Size;

        String cacheStr;        //缓存字符串信息
        if (!mustFromDB) {
            /**
             * 先从缓存获取，若获取不为空，则检查是否过期，不过期则返回对象信息，其他情况统一从数据库获取并重新设置到缓存
             */
            cacheStr = redisService.getHSet(skuCacheKey, skuId + "");
            String[] tempArr;
            if (cacheStr == null) {
                productSkuItem = null;
            } else {
                tempArr = cacheStr.split(expire_time_separate);
                if (System.currentTimeMillis() - Long.parseLong(tempArr[1]) > DsmConcepts.TIMESTAMP_MINUTE * 5) {   //缓存超时
                    productSkuItem = null;
                } else {
                    productSkuItem = JSON.parseObject(tempArr[0], ProductSkuItem.class);
                }
            }
        }

        /**
         * 上述操作未获取到信息，统一从数据库获取并重新设置到缓存
         */
        if (productSkuItem == null) {
            productSkuItem = productSkuDao.getProductSkuItem(skuId);
            if (productSkuItem != null) {
                cacheStr = JSONObject.toJSONString(productSkuItem) + expire_time_separate + System.currentTimeMillis(); //redis缓存时间5分钟
                redisService.setHSet(skuCacheKey, skuId + "", cacheStr);
            } else {
                //这里主要是移失效的key缓存
                redisService.delHSet(skuCacheKey, skuId + "");
            }
        }
        redisService.expire(skuCacheKey, DsmConcepts.MINUTE * 5);
        return productSkuItem;
    }


    @Override
    public List<ProductSkuItem> getProductSkuItemListFromCache(boolean mustFromDB, Long... skuIdArr) {

        String skuCacheKey;     //缓存的key
        String cacheStr;        //缓存字符串信息
        List<ProductSkuItem> productSkuItemList = new ArrayList<>();

        /**
         * 是否强制从数据库获取
         */
        if (!mustFromDB) {  //:是
            /**
             * 循环遍历，直接调用获取单个skuItem缓存的方法
             */
            ProductSkuItem productSkuItem;
            for (Long skuId : skuIdArr) {
                productSkuItem = getProductSkuItemFromCache(false,skuId);
                if(productSkuItem != null){
                    productSkuItemList.add(productSkuItem);
                }
            }
        } else {    //:否
            /**
             * 数据库中查询，并循环设置过期时间 mark
             */
            productSkuItemList = productSkuDao.getProductSkuItems(skuIdArr);
            for (ProductSkuItem item : productSkuItemList) {
                cacheStr = JSONObject.toJSONString(item) + expire_time_separate + System.currentTimeMillis();
                //redis缓存时间5分钟
                skuCacheKey = "productSku_" + item.getSkuId() / cache_hSet_Size;
                redisService.setHSet(skuCacheKey, item.getSkuId() + "", cacheStr);
                redisService.expire(skuCacheKey, DsmConcepts.MINUTE * 5);
            }
        }

        return productSkuItemList.size() == 0 ? null : productSkuItemList;
    }

    @Override
    public void cleanProductSkuItemFromCache(long skuId){
        String skuCacheKey = "productSku_" + skuId / cache_hSet_Size;
        redisService.delHSet(skuCacheKey, skuId + "");
    }


    @Resource
    protected RedisDataSource redisDataSource;





    public boolean doOrderCheckSkuQuantity(long skuId){
        String key = "skuQuantity_"+skuId;
        //获取缓存中的库存数据，如果数据为空，则查询数据库获取
        boolean cacheDecrflag = false;
        ShardedJedis shardedJedis = redisDataSource.getResource();
        try{
            Jedis jedis = shardedJedis.getShard(key);
            int quantity;
            //缓存中处理，用于下单的削峰
            if(!jedis.exists(key)){
                quantity = productSkuDao.getProductSkuItem(skuId).getQuantity();
                jedis.setnx(key,quantity+"");
                jedis.expire(key,30);
            }else{
                quantity = Integer.parseInt(jedis.get(key));
                jedis.expire(key,30);
            }

            if(quantity>0) {
                //防止并发导致超卖的操作
                synchronized (this) {
                    jedis.watch(key);
                    if (Integer.parseInt(jedis.get(key)) > 0) {
                        String skuCacheKey = "productSku_" + skuId / cache_hSet_Size;
                        String cacheObjString = jedis.hget(skuCacheKey, skuId + "");

                        /**
                         * redis事务操作
                         */
                        Transaction transaction = jedis.multi();
                        //1.减库存缓存中的库存数
                        transaction.decr(key);
                        //2.skuItem缓存更新(如果存在)
                        if(StringUtils.isNoneBlank(cacheObjString)){
                            String[] tempArr;
                            tempArr = cacheObjString.split(expire_time_separate);
                            if (System.currentTimeMillis() - Long.parseLong(tempArr[1]) < DsmConcepts.TIMESTAMP_MINUTE * 5) {   //缓存未超时
                                ProductSkuItem productSkuItem = JSON.parseObject(tempArr[0], ProductSkuItem.class);
                                productSkuItem.setQuantity(quantity-1);
                                transaction.hset(skuCacheKey,skuId+"",JSONObject.toJSONString(productSkuItem)+expire_time_separate + System.currentTimeMillis());
                            }
                        }
                        transaction.exec();
                        cacheDecrflag = true;
                    }else{
                        jedis.unwatch();
                    }
                }
            }else{
                //库存不足，无法完成下单操作，返回错误提示信息
            }
        }catch (Exception ex){

        }finally {
            if(shardedJedis != null)shardedJedis.close();
        }
        return cacheDecrflag;
    }



}
