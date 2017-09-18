package com.dsm.model.cart;

import com.dsm.model.product.ProductSkuItem;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/29
 *
 * @author : Lbwwz
 *         <p/>
 *         购物车信息对象
 */
public class ShoppingCartItem extends ProductSkuItem{

    private static final long serialVersionUID = -4560145330523945661L;
    //购物车主键id
    private Integer cartItemId;

    //用户id
    private Integer userId;


    //购物车中商品数量
    private Integer cartItemNum;

    //购物车中商品选中状态
    private Integer isSelected;

    //库存是否满足
    private boolean isEnough = true;


    public ShoppingCartItem() {
        super();
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.lastUpdateTime = new Timestamp(System.currentTimeMillis());
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        this.isSelected = isSelected == null?0:isSelected;
    }

    public boolean getIsEnough() {
        return isEnough;
    }

    public void setIsEnough(boolean enough) {
        isEnough = enough;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartItem)) return false;

        ShoppingCartItem that = (ShoppingCartItem) o;

        if (cartItemId != null ? !cartItemId.equals(that.cartItemId) : that.cartItemId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (cartItemNum != null ? !cartItemNum.equals(that.cartItemNum) : that.cartItemNum != null) return false;
        return isSelected != null ? isSelected.equals(that.isSelected) : that.isSelected == null;

    }

    @Override
    public int hashCode() {
        int result = cartItemId != null ? cartItemId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (cartItemNum != null ? cartItemNum.hashCode() : 0);
        result = 31 * result + (isSelected != null ? isSelected.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "cartItemId=" + cartItemId +
                ", userId=" + userId +
                ", cartItemNum=" + cartItemNum +
                ", isSelected=" + isSelected +
                ", isEnough=" + isEnough +
                ", skuId=" + skuId +
                ", propertiesName='" + propertiesName + '\'' +
                ", mainImage='" + mainImage + '\'' +
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
