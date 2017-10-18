package com.dsm.model.order;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/17
 *
 * @author : Lbwwz
 */
public class OrderPackage {

    //店铺id
    private Long shopId;
    //店铺名称
    private String shopName;

    //订单信息
    private List<OrderSkuItem> orderSkuItemList;



//    private

    //运费信息（模块待定）

    //店铺商品合计
    private BigDecimal shopProductPrice;



    public OrderPackage(Long shopId, String shopName, List<OrderSkuItem> orderSkuItemList) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.orderSkuItemList = orderSkuItemList;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<OrderSkuItem> getOrderSkuItemList() {
        return orderSkuItemList;
    }

    public void setOrderSkuItemList(List<OrderSkuItem> orderSkuItemList) {
        this.orderSkuItemList = orderSkuItemList;
    }

    public BigDecimal getShopTotalPrice() {
        return shopProductPrice;
    }

    public void setShopTotalPrice(BigDecimal shopTotalPrice) {
        this.shopProductPrice = shopTotalPrice;
    }

    @Override
    public String toString() {
        return "OrderPackage{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", orderSkuItemList=" + orderSkuItemList +
                ", shopProductPrice=" + shopProductPrice +
                '}';
    }
}
