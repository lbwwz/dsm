package com.dsm.model.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/29
 *
 * @author : Lbwwz
 *         <p/>
 *         购物车信息对象
 */
public class ShoppingCart {


    //商家分包对象,将商品按照商家分组
    private List<CartPackage> cartPackages;

    //商品总额
    private BigDecimal totalPrice;

    //运费（需要根据分包计算总和）---待定
    private BigDecimal freight;


    //其他提示信息


    public ShoppingCart() {
    }

    public List<CartPackage> getCartPackages() {
        return cartPackages;
    }

    public void setCartPackages(List<CartPackage> cartPackages) {
        this.cartPackages = cartPackages;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public void addCartPackage(CartPackage cartPackage){
        this.cartPackages = this.cartPackages==null?new ArrayList<>():this.cartPackages;
        this.cartPackages.add(cartPackage);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartPackages=" + cartPackages +
                ", totalPrice=" + totalPrice +
                ", freight=" + freight +
                '}';
    }
}
