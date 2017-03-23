package com.dsm.dao;


import com.dsm.model.product.AttrValueBean;
import com.dsm.model.product.ProductAttrBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 产品的属性操作DAO接口
 *
 * @author lbwwz
 */

@Repository
public interface IProductAttrDao {
    /**
     * 根据添加商品时所在的类别获取所有该类别下商品的销售属性（用于卖家家添加商品的操作页）
     *
     * @param catId 类目ID
     */
    List<ProductAttrBean> getAllSaleAttr(String catId);

    /**
     * 根据添加商品时选择的类别获取所有该类别下商品的基础属性（用于卖家家添加商品的操作页）
     *
     * @param catId 类目ID
     */
    List<ProductAttrBean> getAllBaseAttr(String catId);

    /**
     * 获取某个类目下的全部属性（不含属性值）
     *
     * @param catId 类目ID
     */
    List<ProductAttrBean> getAttrByCat(int catId);

    /**
     * 获取某个产品属类目下的全部属性（含属性值）
     *
     * @param m 封装参数的 map（参数如下）
     *          ├── catId:类目ID
     *          └── status:属性名状态字段，负数表示忽略该条件
     */
    List<ProductAttrBean> getAttrNameByCat(Map m);

    /**
     * 根据属性ID查询状态可用属性信息
     *
     * @param AttrId 属性ID
     * @return id对应的属性信息封装
     */
    ProductAttrBean getUsableAttrById(int AttrId);

    /**
     * 根据属性ID查询相应的属性信息
     *
     * @param m 封装参数的 map（参数如下）
     *          ├── attrId:属性ID
     *          └── status:属性名状态字段，负数表示忽略该条件
     * @return id对应的属性名信息
     */
    ProductAttrBean getAttrNameById(Map m);

    /**
     * 添加一条商品属性，返回该产品的主键：产品属性ID
     *
     * @param beans 商品属性信息封装
     */
    int addAttr(List<ProductAttrBean> beans);

    /**
     * 为商品属性添加属性值
     *
     * @param beans 属性值信息封装
     */
    int addAttrValues(List<AttrValueBean> beans);

    /**
     * 变更产品属性的可用状态
     *
     * @param attrId 属性ID
     */
    int changeAttrStatus(Integer attrId);

    /**
     * 更新产品属性的基本信息
     *
     * @param bean 商品属性信息封装
     */
    Integer updateAttr(ProductAttrBean bean);

    /**
     * 对属性序列值进行重排序
     *
     * @param order
     */
    void reorder(List<String> order);



    /**
     * 根据商品的属性ID获取该属性下所有的初始属性值
     *
     * @param m 封装参数的 map（参数如下）
     *          ├── attrId:属性ID
     *          └── status:属性值状态字段，负数表示忽略该条件
     */
    List<AttrValueBean> getValuesByAttrId(Map m);

    /**
     *
     * @param m
     * @return
     */
    AttrValueBean getValueById(Map m);


    /**
     * 更新属性信息
     * @param bean
     * @return
     */
    Integer updateAttrValue(AttrValueBean bean);

    /**
     * 更改具体属性值的可用状态
     *
     * @param valueId 属性值ID
     */
    Integer changeValueStatus(Integer valueId);
}
