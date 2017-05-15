package com.dsm.dao;

import com.dsm.model.product.Sku;
import org.springframework.stereotype.Component;

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
@Component
public interface IProductSkuDao {
    /**
     * 为商品表添加商品信息
     */
    Integer addSkuItem(Sku sku);


    /**
     * 为商品添加一组sku
     */
    Integer addSkuItemList(List<Sku> skuList);

    /**
     */
    List<Sku> getSkuListByProductId(int productId);



}
