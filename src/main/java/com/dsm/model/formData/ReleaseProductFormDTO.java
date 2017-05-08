package com.dsm.model.formData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/24
 *
 * @author : Lbwwz
 * 发布商品的信息映射和封装对象
 */
public class ReleaseProductFormDTO implements Serializable{

    private static final long serialVersionUID = 1957094872887981891L;
    //商品名称
    private String productName;

    //商品添加的类目
    private Integer catId;
    //品牌
    private Integer brand;
    //市场价
    private BigDecimal productMarketPrice;
    //搜索关键字
    private String keywords;
    //基本属性
    private String[] baseAttrInfo;

    //自定义属性名和属性值
    private String[] customBaseAttrName;
    private String[] customBaseAttrValue;
    //sku的properties信息数据
    private String[] property;
    //sku商品价格
    private BigDecimal[] productPrice;
    //sku商品数量
    private String[] productCount;
    //sku商品的商家自定义编码
    private String[] customProductNum;
    //商品图片
    private String[] imgItem;
    //商品图
    private String mainImgItem;
    //商品详情
    private String detailContent;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public BigDecimal getProductMarketPrice() {
        return productMarketPrice;
    }

    public void setProductMarketPrice(BigDecimal productMarketPrice) {
        this.productMarketPrice = productMarketPrice;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String[] getBaseAttrInfo() {
        return baseAttrInfo;
    }

    public void setBaseAttrInfo(String[] baseAttrInfo) {
        this.baseAttrInfo = baseAttrInfo;
    }

    public String[] getCustomBaseAttrName() {
        return customBaseAttrName;
    }

    public void setCustomBaseAttrName(String[] customBaseAttrName) {
        this.customBaseAttrName = customBaseAttrName;
    }

    public String[] getCustomBaseAttrValue() {
        return customBaseAttrValue;
    }

    public void setCustomBaseAttrValue(String[] customBaseAttrValue) {
        this.customBaseAttrValue = customBaseAttrValue;
    }

    public String[] getProperty() {
        return property;
    }

    public void setProperty(String[] property) {
        this.property = property;
    }

    public BigDecimal[] getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal[] productPrice) {
        this.productPrice = productPrice;
    }

    public String[] getProductCount() {
        return productCount;
    }

    public void setProductCount(String[] productCount) {
        this.productCount = productCount;
    }

    public String[] getCustomProductNum() {
        return customProductNum;
    }

    public void setCustomProductNum(String[] customProductNum) {
        this.customProductNum = customProductNum;
    }

    public String[] getImgItem() {
        return imgItem;
    }

    public void setImgItem(String[] imgItem) {
        this.imgItem = imgItem;
    }

    public String getMainImgItem() {
        return mainImgItem;
    }

    public void setMainImgItem(String mainImgItem) {
        this.mainImgItem = mainImgItem;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    @Override
    public String toString() {
        return "ReleaseProductFormDTO{" +
                "productName='" + productName + '\'' +
                ", catId=" + catId +
                ", brand=" + brand +
                ", productMarketPrice=" + productMarketPrice +
                ", keywords='" + keywords + '\'' +
                ", baseAttrInfo=" + Arrays.toString(baseAttrInfo) +
                ", customBaseAttrName=" + Arrays.toString(customBaseAttrName) +
                ", customBaseAttrValue=" + Arrays.toString(customBaseAttrValue) +
                ", property=" + Arrays.toString(property) +
                ", productPrice=" + Arrays.toString(productPrice) +
                ", productCount=" + Arrays.toString(productCount) +
                ", customProductNum=" + Arrays.toString(customProductNum) +
                ", imgItem=" + Arrays.toString(imgItem) +
                ", mainImgItem='" + mainImgItem + '\'' +
                ", detailContent='" + detailContent + '\'' +
                '}';
    }
}
