package com.dsm.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer productId;
    //店铺ID
    private Integer shopId;
    //分类ID
    private Integer catId;
    //品牌ID
    private Integer brandId;
    //商品名称
    private String productName;
    //商品编
    private String productSn;
    //商品主图
    private String mainImage;
    //商品详情信息
    private String productDesc;
    //商品的最小价格
    private BigDecimal minPrice;
    //商品的最大价格
    private BigDecimal maxPrice;
    //商品的关键词
    private String keywords;
    //商品排序，该字段仅用于店铺内显示的商品排序
    private Integer sort;
    //商品状态 0，草稿；1，上架；2，下架；3，禁售
    private int status;
    //商品添加时间
    private String createTime;
    //商品最后更新的时间
    private String lastUpdateTime;

    public ProductBean() {}

    public ProductBean(String keywords, String productDesc, String mainImage, String productName, Integer brandId, Integer catId, Integer shopId) {
        this.keywords = keywords;
        this.productDesc = productDesc;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "productId=" + productId +
                ", shopId=" + shopId +
                ", catId=" + catId +
                ", brandId=" + brandId +
                ", productName='" + productName + '\'' +
                ", productSn='" + productSn + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", keywords='" + keywords + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                '}';
    }
}
