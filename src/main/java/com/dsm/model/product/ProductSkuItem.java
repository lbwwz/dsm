package com.dsm.model.product;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/13
 *
 * @author : Lbwwz
 *         <p/>
 *         单条sku商品基本信息(购物车页，订单结算页显示使用)
 */
public class ProductSkuItem extends ProductBean{
    //skuId
    public String skuId;
    //组合销售属性信息
    public String propertiesName;
    //该商品的数量
    public String quantity;
    //该商品的单价
    public String skuPrice;
    //该商品的商品码
    public String shopSn;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getShopSn() {
        return shopSn;
    }

    public void setShopSn(String shopSn) {
        this.shopSn = shopSn;
    }

    @Override
    public String toString() {
        return "ProductSkuItem{" +
            "skuId='" + skuId + '\'' +
            ", propertiesName='" + propertiesName + '\'' +
            ", quantity='" + quantity + '\'' +
            ", skuPrice='" + skuPrice + '\'' +
            ", shopSn='" + shopSn + '\'' +
            ", productId=" + productId +
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
