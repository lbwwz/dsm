package com.dsm.model.seller;

/**
 * 店铺动态评分信息
 *
 * @author lbwwz
 */
public class ShopScore {

    private int shopScope;

    //商品的描述评分
    private String goodsScope;

    //服务评分
    private String serviceScore;

    //发货速度评分
    private String deliveryScore;

    public ShopScore() {
    }

    public int getShopScope() {
        return shopScope;
    }

    public void setShopScope(int shopScope) {
        this.shopScope = shopScope;
    }

    public String getGoodsScope() {
        return goodsScope;
    }

    public void setGoodsScope(String goodsScope) {
        this.goodsScope = goodsScope;
    }

    public String getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(String serviceScore) {
        this.serviceScore = serviceScore;
    }

    public String getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(String deliveryScore) {
        this.deliveryScore = deliveryScore;
    }


}
