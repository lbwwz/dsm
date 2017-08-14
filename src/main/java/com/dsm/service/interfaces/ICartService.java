package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;
import com.dsm.model.cart.ShoppingCart;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/15
 *
 * @author : Lbwwz
 *         <p/>
 *         执行购物车操作的service
 */
public interface ICartService {
    /**
     * 增减商品到购物车
     * @param skuId 商品sku
     * @param changeCount 变更数量商品数量（增加changeCount件，减少传负数）
     * @param cookieEnabled 浏览器客户端是否支出cookie缓存（若不知处，需要用户进行登录操作）
     * @return
     */
    BackMsg<String> addOrMinusToCart(Integer skuId, int changeCount, boolean cookieEnabled);


    ShoppingCart getMyShoppingCart();



}
