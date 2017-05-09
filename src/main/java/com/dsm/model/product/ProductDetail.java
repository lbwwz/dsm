package com.dsm.model.product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/9
 *
 * @author : Lbwwz
 * <p/>
 * 商品详情的信息封装
 */
public class ProductDetail {
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
    //商品图片
    private List<ProductImageItem> productImages;
    //商品详情信息
    private String productDesc;
    //商品的最小价格
    private BigDecimal minPrice;
    //商品的最大价格
    private BigDecimal maxPrice;
    //商品状态 0，草稿；1，上架；2，下架；3，禁售
    private int status;
    //商品添加时间
    private String createTime;
    //商品最后更新的时间
    private String lastUpdateTime;
    //基本属性
    private List<ProductDetailAttrInfo> baseAttrsInfo;
    //自定义属性
    private List<ProductDetailAttrInfo> customerAttrsInfo;
    //商品sku
    private List<Sku> skuList;

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

    public List<ProductImageItem> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImageItem> productImages) {
        this.productImages = productImages;
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

    public List<ProductDetailAttrInfo> getBaseAttrsInfo() {
        return baseAttrsInfo;
    }

    public void setBaseAttrsInfo(List<ProductDetailAttrInfo> baseAttrsInfo) {
        this.baseAttrsInfo = baseAttrsInfo;
    }

    public List<ProductDetailAttrInfo> getCustomerAttrsInfo() {
        return customerAttrsInfo;
    }

    public void setCustomerAttrsInfo(List<ProductDetailAttrInfo> customerAttrsInfo) {
        this.customerAttrsInfo = customerAttrsInfo;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productId=" + productId +
                ", shopId=" + shopId +
                ", catId=" + catId +
                ", brandId=" + brandId +
                ", productName='" + productName + '\'' +
                ", productSn='" + productSn + '\'' +
                ", productImages=" + productImages +
                ", productDesc='" + productDesc + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", baseAttrsInfo=" + baseAttrsInfo +
                ", customerAttrsInfo=" + customerAttrsInfo +
                ", skuList=" + skuList +
                '}';
    }
}
