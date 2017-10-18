package com.dsm.model.cart;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/29
 *
 * @author : Lbwwz
 *         <p/>
 *         关于购物车数据表字段对应的PO对象
 */
public class ShoppingCartItemPO {

    private Long cartItemId;

    private Long userId;

    private Long shopId;

    private Long skuId;

    private Integer cartItemNum;

    private Integer isSelected = 1;

    private Timestamp createTime;
    private Timestamp updateTime;

    public ShoppingCartItemPO() {
    }

    public ShoppingCartItemPO(Long userId, Long shopId, Long skuId, Integer cartItemNum, Integer isSelected) {
        this.userId = userId;
        this.shopId = shopId;
        this.skuId = skuId;
        this.cartItemNum = cartItemNum;
        this.isSelected = isSelected;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public Integer getCartItemNum() {
        return cartItemNum;
    }

    public void setCartItemNum(Integer cartItemNum) {
        this.cartItemNum = cartItemNum;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
