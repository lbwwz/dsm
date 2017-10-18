package com.dsm.model.product;

import java.io.Serializable;
import java.util.Set;

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
    private Long baseAttrId;
    //详情自定义基本属性ID
    private Long customAttrId;
    //商品id
    private Long productId;
    //固有属性id
    private Long attrId;
    //固有属性对应的属性名
    private String attrName;
    //固有属性对定的属性值ID
    private Long valueId;
    //固有属性对定的属性值
    private String attrValue;

    /*针对销售属性的属性值*/
    private Set<AttrValueBean> saleAttrValues;


    //排序
    private Integer sort;
    //详情商品属性状态
    private int status;

    public ProductDetailAttrInfo(){

    }
    public ProductDetailAttrInfo(Long productId,Long attrId,String attrName,Long valueId,String attrValue){
        this.productId = productId;
        this.attrId = attrId;
        this.attrName = attrName;
        this.valueId = valueId;
        this.attrValue = attrValue;
    }
    public ProductDetailAttrInfo(Long productId,String attrName,String attrValue){
        this.productId = productId;
        this.attrName = attrName;
        this.attrValue = attrValue;
    }
    public ProductDetailAttrInfo(Long attrId,String attrName,Set<AttrValueBean> saleAttrValues){
        this.attrId = attrId;
        this.attrName = attrName;
        this.saleAttrValues = saleAttrValues;
    }

    public Long getBaseAttrId() {
        return baseAttrId;
    }

    public void setBaseAttrId(Long baseAttrId) {
        this.baseAttrId = baseAttrId;
    }

    public Long getCustomAttrId() {
        return customAttrId;
    }

    public void setCustomAttrId(Long customAttrId) {
        this.customAttrId = customAttrId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
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

    public Set<AttrValueBean> getSaleAttrValues() {
        return saleAttrValues;
    }

    public void setSaleAttrValues(Set<AttrValueBean> saleAttrValues) {
        this.saleAttrValues = saleAttrValues;
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
                ", saleAttrValues=" + saleAttrValues +
                ", sort=" + sort +
                ", status=" + status +
                '}';
    }
}
