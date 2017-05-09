package com.dsm.dao;

import com.dsm.model.product.ProductBean;
import com.dsm.model.product.ProductDetail;
import com.dsm.model.product.ProductDetailAttrInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 *         <p>
 *         商品操作的dao
 */
@Component
public interface IProductDao {
    /**
     * 为商品表添加商品信息
     */
    Integer addProductInfo(ProductBean bean);

/**
     * 为商品表添加商品信息
     */
    Integer addProductInfoList(List<ProductBean> beans);


    /**
     * 根据商品id查询相关的商品信息
     * @param productId 商品Id
     * @return
     */
    ProductBean getProductBaseInfo(Integer productId);

    /**
     *
     * @param productId
     * @return
     */
    ProductDetail getProductDetailInfo(Integer productId);

    /**
     * 添加商品的基本属性
     * @param info 要添加的基本属性信息
     * @return
     */
    Integer addProductBaseAttrInfo(List<ProductDetailAttrInfo> info);

    /**
     * 获取商品的基本属性
     * @param productId productId
     * @return
     */
    List<ProductDetailAttrInfo> getProductBaseAttrInfo(Integer productId);

    /**
     * 为发布的商品添加自定义属性
     * @param info 要添加自定义属性信息
     * @return
     */
    Integer addProductCustomAttrInfo(List<ProductDetailAttrInfo> info);

    /**
     * 获取商品的自定义属性
     * @param productId
     * @return
     */
    List<ProductDetailAttrInfo> getProductCustomAttrInfo(Integer productId);


    /**
     * 根据关键字查询相关的商品信息
     * @param keyParams 查询信息封装
     *              ├── start: 查询页面起点
     *              └── num: 查询的数量
     * @return 商品信息
     */
    List<ProductBean> getProductByKey(Map<String,Object> keyParams);

}
