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
public class ProductBean implements Serializable{
    private static final long serialVersionUID = -936896536518602212L;
    //产品ID
    protected Integer productId;
    //店铺ID
    protected Integer shopId;
    //店铺名称
    protected String shopName;
    //分类ID
    protected Integer catId;
    //品牌ID
    protected Integer brandId;
    //商品名称
    protected String productName;
    //商品编
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
    //商品排序，该字段仅用于店铺内显示的商品排序
    protected Integer sort;
    //商品状态 0，草稿；1，上架；2，下架；3，禁售
    protected int status;
    //是否精品
    protected int isBest;
    //是否新品
    protected int isNew;
    //是否热销
    protected int isHot;

    //商品添加时间
    protected Timestamp createTime;
    //商品最后更新的时间
    protected Timestamp lastUpdateTime;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
