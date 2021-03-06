package com.dsm.dao;

import com.dsm.model.product.ProductSkuItem;
import com.dsm.model.product.Sku;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 *         <p>
 *         商品sku操作的dao
 */
@Repository
public interface IProductSkuDao {
    /**
     * 为sku表添加sku信息
     */
    Integer addSkuItem(Sku sku);


    /**
     * 为商品添加一组sku
     */
    Integer addSkuItemList(List<Sku> skuList);

    /**
     * 通过商品号查询sku列表信息
     * @param productId 商品号
     */
    List<Sku> getSkuListByProductId(long productId);



    ProductSkuItem getProductSkuItem(long skuId);

    List<ProductSkuItem> getProductSkuItems(Long ... skuIdArr);


    /**
     * 查询sku 单品的库存
     */
    int getSkuQuantity(long skuId);



}
