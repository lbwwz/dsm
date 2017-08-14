package com.dsm.model.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 产品类的封装（用于卖家商品发布页面的信息封装）
 *
 * @author lbwwz
 *
 *
 */
public class ProductBean extends ProductBaseBean implements Serializable{
    private static final long serialVersionUID = -936896536518602212L;
    //商品编号
    protected String productSn;
    //商品主图
    protected String mainImage;
    //商品简述
    protected String productBrief;
    //商品的最小价格
    protected BigDecimal minPrice;
    //商品的最大价格
    protected BigDecimal maxPrice;
    //商品的关键词
    protected String keywords;
    //是否精品
    protected int isBest;
    //是否新品
    protected int isNew;
    //是否热销
    protected int isHot;

    public ProductBean() {}

    public ProductBean(String keywords,String productBrief, String mainImage, String productName, Integer brandId, Integer catId, Integer shopId) {
        this.keywords = keywords;
        this.productBrief = productBrief;
        this.mainImage = mainImage;
        this.productName = productName;
        this.brandId = brandId;
        this.catId = catId;
        this.shopId = shopId;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getProductBrief() {
        return productBrief;
    }

    public void setProductBrief(String productBrief) {
        this.productBrief = productBrief;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getIsBest() {
        return isBest;
    }

    public void setIsBest(int isBest) {
        this.isBest = isBest;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "productId=" + productId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", catId=" + catId +
                ", brandId=" + brandId +
                ", productName='" + productName + '\'' +
                ", productSn='" + productSn + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", productBrief='" + productBrief + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", keywords='" + keywords + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", isBest=" + isBest +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
