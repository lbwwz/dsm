package com.dsm.model.cart;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/8/14
 *
 * @author : Lbwwz
 * <p>购物车包裹，根据店铺将购物车中的子项进行分包操作的数据结构</p>
 */
public class CartPackage {

    private Integer shopId;
    private String shopName;

    private List<ShoppingCartItem> CartItems;

    public CartPackage(Integer shopId, String shopName, List<ShoppingCartItem> cartItems) {
        this.shopId = shopId;
        this.shopName = shopName;
        CartItems = cartItems;
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

    public List<ShoppingCartItem> getCartItems() {
        return CartItems;
    }

    public void setCartItems(List<ShoppingCartItem> cartItems) {
        CartItems = cartItems;
    }
}
