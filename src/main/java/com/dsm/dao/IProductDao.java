package com.dsm.dao;

import com.dsm.model.product.*;
import org.apache.ibatis.annotations.Param;
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
     * 为商品表添加商品基本信息
     */
    Integer addProductInfo(ProductBean bean);

    /**
     * 添加商品详情信息
     */
    Integer addGraphicDetail(GraphicDetail detail);

    /**
     * 添加商品图片列表
     */
    Integer addProductImageList(List<ProductImageItem> imgs);

    /**
     * 根据商品id查询相关的商品信息
     * @param productId 商品Id
     */
    ProductBean getProductBaseInfoById(Integer productId);

    /**
     * 查询商品的详情信息
     * @param productId 商品id
     */
    ProductDetail getProductDetailInfo(Integer productId);


    /**
     * 查询商品的图片信息
     * @param productId 商品id
     */
    ProductDetail getProductImageList(Integer productId);


    /**
     * 为发布的商品添加基本属性
     * @param info 要添加的基本属性信息
     */
    Integer addProductBaseAttrList(List<ProductDetailAttrInfo> info);

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
    Integer addProductCustomAttrList(List<ProductDetailAttrInfo> info);

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



    List<ProductBean> getPageByCategoryWithWeighted(@Param("catId")Integer catId, @Param("start")int start,
                                                    @Param("offset")int offset,  @Param("queryWeight")String ... queryWeight);


    List<ProductBean> getPageByCategoryByPrice(@Param("catId")Integer catId, @Param("start")int start,
                                               @Param("offset")int offset, @Param("sortType")int sortType);
}
