package com.dsm.dao;

import com.dsm.model.product.ProductBean;
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

    ProductBean getProduct(Integer goodsId);

    /**
     * 根据关键字查询相关的商品信息
     * @param keyParams 查询信息封装
     *              ├── start: 查询页面起点
     *              └── num: 查询的数量
     * @return 商品信息
     */
    List<ProductBean> getProductByKey(Map<String,Object> keyParams);

}
