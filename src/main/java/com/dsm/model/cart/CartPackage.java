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

    private Long shopId;
    private String shopName;
    private boolean isSelected;

    private List<ShoppingCartItem> cartItems;

    public CartPackage(long shopId, String shopName, List<ShoppingCartItem> cartItems) {
        this.shopId = shopId;
        this.shopName = shopName;
        setCartItems(cartItems);
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


    public boolean getIsSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
        this.isSelected = true;
        for(ShoppingCartItem item : cartItems){
            if(item.getIsSelected()!= 1){
                this.isSelected = false;
                return;
            }
        }
    }


    @Override
    public String toString() {
        return "CartPackage{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }
}
