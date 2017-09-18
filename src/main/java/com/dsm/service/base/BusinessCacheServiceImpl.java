package com.dsm.service.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.dao.IProductSkuDao;
import com.dsm.model.product.ProductSkuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private String expire_time_separate = "@=@";

    @Override
    public ProductSkuItem getProductSkuItemFromCache(Integer skuId, boolean mustFromDB) {
        if (skuId == null) {
            return null;
        }
        ProductSkuItem productSkuItem = null;
        /**
         * 改用哈希结构减少redis中要维护的key的数量
         */
        String skuCacheKey = "productSku_" +skuId/cache_hSet_Size;

        String cacheStr;
        if (!mustFromDB) {
            cacheStr = redisService.getHSet(skuCacheKey, skuId+"");
            String[] tempArr;
            if(cacheStr == null){
                productSkuItem = null;
            }else{
                tempArr = cacheStr.split(expire_time_separate);
                if(System.currentTimeMillis()-Long.parseLong(tempArr[1])>DsmConcepts.TIMESTAMP_MINUTE*5){
                    productSkuItem = null;
                }else{
                    productSkuItem = JSON.parseObject(tempArr[0],ProductSkuItem.class);
                }
            }
        }
        if (productSkuItem == null) {
            productSkuItem = productSkuDao.getProductSkuItem(skuId);
            if (productSkuItem != null) {
                cacheStr = JSONObject.toJSONString(productSkuItem)+expire_time_separate+System.currentTimeMillis();
                //redis缓存时间5分钟
                redisService.setHSet(skuCacheKey,skuId+"", cacheStr);
            }else{
                redisService.delHSet(skuCacheKey,skuId+"");
            }
        }
        redisService.expire(skuCacheKey,DsmConcepts.MINUTE*5);
        return productSkuItem;
    }

}
