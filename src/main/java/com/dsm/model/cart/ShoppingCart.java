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

    //选中商品的总件数
    private int selectTotalNum;

    //运费（需要根据分包计算总和）---待定
    private BigDecimal freight;

    //所有商品是否全部选中
    private boolean selectedAll = true;


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

    public int getSelectTotalNum() {
        return selectTotalNum;
    }

    public void setSelectTotalNum(int selectTotalNum) {
        this.selectTotalNum = selectTotalNum;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public boolean isSelectedAll() {
        return selectedAll;
    }

    public void setSelectedAll(boolean selectedAll) {
        this.selectedAll = selectedAll;
    }

    public void addCartPackage(CartPackage cartPackage){
        this.cartPackages = this.cartPackages==null?new ArrayList<>():this.cartPackages;
        this.cartPackages.add(cartPackage);
        if(!cartPackage.getIsSelected()){
            this.selectedAll = false;
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartPackages=" + cartPackages +
                ", totalPrice=" + totalPrice +
                ", selectTotalNum=" + selectTotalNum +
                ", freight=" + freight +
                ", selectedAll=" + selectedAll +
                '}';
    }
}
