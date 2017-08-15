package com.dsm.model.product;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/13
 *
 * @author : Lbwwz
 *         <p/>
 *         单条sku商品基本信息(购物车页，订单结算页显示使用)
 */
public class ProductSkuItem extends ProductBaseBean{
    //skuId
    public Integer skuId;
    //组合销售属性信息
    public String propertiesName;
    //该商品的数量
    public Integer quantity;
    //该商品的单价
    public BigDecimal skuPrice;
    //该商品的商品码
    public String shopSn;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
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
                "skuId=" + skuId +
                ", propertiesName='" + propertiesName + '\'' +
                ", quantity=" + quantity +
                ", skuPrice=" + skuPrice +
                ", shopSn='" + shopSn + '\'' +
                ", productId=" + productId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", catId=" + catId +
                ", brandId=" + brandId +
                ", productName='" + productName + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
