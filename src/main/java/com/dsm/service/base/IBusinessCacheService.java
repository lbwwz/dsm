package com.dsm.service.base;

import com.dsm.model.product.ProductSkuItem;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/16
 *
 * @author : Lbwwz
 * 缓存的基本操作业务(不涉及权限等方面的公共业务操作)
 */

public interface IBusinessCacheService {


    /**
     * 通过缓存获取商品sku信息
     * @param skuId 商品skuId
     * @param mustFromDB 是否强制从数据库获取
     */
    ProductSkuItem getProductSkuItemFromCache(Integer skuId,boolean mustFromDB);
}
