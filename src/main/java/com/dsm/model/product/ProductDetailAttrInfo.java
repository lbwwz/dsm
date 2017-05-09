package com.dsm.model.product;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/9
 *
 * @author : Lbwwz
 * <p/>
 * 商品详情的商品属性信息
 */
public class ProductDetailAttrInfo implements Serializable{

    private static final long serialVersionUID = -5182449799919024544L;
    //详情商品基本属性ID
    private Integer baseAttrId;
    //详情自定义基本属性ID
    private Integer customAttrId;
    //商品id
    private Integer productId;
    //固有属性id
    private Integer attrId;
    //固有属性对应的属性名
    private String attrName;
    //固有属性对定的属性值ID
    private Integer valueId;
    //固有属性对定的属性值
    private String attrValue;
    //排序
    private Integer sort;
    //详情商品属性状态
    private int status;

    public Integer getBaseAttrId() {
        return baseAttrId;
    }

    public void setBaseAttrId(Integer baseAttrId) {
        this.baseAttrId = baseAttrId;
    }

    public Integer getCustomAttrId() {
        return customAttrId;
    }

    public void setCustomAttrId(Integer customAttrId) {
        this.customAttrId = customAttrId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDetailAttrInfo{" +
                "baseAttrId=" + baseAttrId +
                ", customAttrId=" + customAttrId +
                ", productId=" + productId +
                ", attrId=" + attrId +
                ", attrName='" + attrName + '\'' +
                ", valueId=" + valueId +
                ", attrValue='" + attrValue + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                '}';
    }
}
