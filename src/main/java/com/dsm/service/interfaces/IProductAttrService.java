package com.dsm.service.interfaces;

import com.dsm.model.product.AttrValueBean;
import com.dsm.model.product.ProductAttrBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/1/17
 *
 * @author : Lbwwz
 */
public interface IProductAttrService {

    /**
     * 获取某个产品属类目下的全部属性(状态可用的)
     *
     * @param catId 类目ID
     */
    List<ProductAttrBean> getAttrByCat(int catId);

    /**
     * 获取某个产品属类目下的全部属性
     *
     * @param catId 类目ID
     */
    List<ProductAttrBean> getAttrNameByCat(int catId, int status);

    /**
     * 根据属性ID查询状态可用属性信息
     * @param attrId 属性id
     */
    ProductAttrBean getUsableAttrById(int attrId);

    /**
     * 添加属性新信息
     *
     * @param catId 类目ID
     * @param isSale 是否销售属性
     * @param isKey 是否关键属性
     * @param isMust 是否必填属性
     * @param attrsName 属性名称
     */
    List<ProductAttrBean> addAttrInfo(Integer catId, Integer isSale, Integer isKey, Integer isMust, String ... attrsName);

    /**
     * 更新属性操作
     * @param bean 更新的信息封装
     */
    ProductAttrBean updateAttrInfo(ProductAttrBean bean);

    /**
     * 变更商品的属性状态
     *
     * @param attrId 属性ID
     */
    boolean changeAttrStatus(Integer attrId);



    /******** 属性值操作 start ********/

    List<AttrValueBean> addAttrValueInfo(Integer attrId,Integer status, String ... valuesName);

    /**
     * 根据商品的属性ID获取该属性下所有的初始属性值
     *
     * @param attrId 属性ID
     */
    List<AttrValueBean> getAttrValues(int attrId, int status);


    AttrValueBean updateAttrValue(int valueId, String AttrValue);

    /**
     * 变更商品的属性值状态
     *
     * @param valueId 属性值ID
     */
    boolean changeAttrValueStatus(Integer valueId);


    List<ProductAttrBean> getKeyAttrList(Integer catId);
}
