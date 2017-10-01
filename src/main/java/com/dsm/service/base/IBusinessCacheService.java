package com.dsm.service.base;

import com.dsm.model.product.ProductSkuItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/16
 *
 * @author : Lbwwz
 *         缓存的基本操作业务(不涉及权限等方面的公共业务操作)
 */

public interface IBusinessCacheService {


    /**
     * 通过缓存获取商品sku信息
     *
     * @param mustFromDB 是否强制从数据库获取
     *                   <p>false时先从缓存获取，若不存在再从数据库获取，true是忽略缓存，直接从数据库获取并更新到缓存</p>
     * @param skuId      商品skuId
     */
    ProductSkuItem getProductSkuItemFromCache(boolean mustFromDB, Integer skuId);

    /**
     * 通过缓存获取商品列表信息信息
     * <p>一次获取多个
     *
     * @param skuIdArr   商品skuId
     * @param mustFromDB 是否强制从数据库获取
     */
    List<ProductSkuItem> getProductSkuItemListFromCache(boolean mustFromDB, Integer... skuIdArr);
}
