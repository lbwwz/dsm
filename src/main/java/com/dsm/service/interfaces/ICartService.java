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
     * 查询当前用户的购物车信息
     */
    ShoppingCart getMyShoppingCart();


    /**
     * 获取当前购物车商品数量
     */
    int getCartSize();

    /**
     * 增减商品到购物车
     *
     * @param skuId       商品sku
     * @param changeCount 变更数量商品数量（增加changeCount件，减少传负数）
     */
    BackMsg<String> addOrMinusToCart(Integer skuId, int changeCount);





    /**
     * 变更购物车中商品项的数量
     *
     * @param skuId        商品skuId
     * @param changedCount 改变成该数量值
     */
    BackMsg<String> changeNumInCart(Integer skuId, Integer changedCount);

    /**
     * 变更购物车中商品项选中状态
     *
     * @param id         根据type的不同表示不同的含义，可以是skuId或者shopId
     * @param isSelected 选中状态，0表示不选中，1表示选中
     * @param type       类型，可以是sku shop 和 all
     */
    BackMsg<String> changeItemsSelected(Integer id, int isSelected, String type);


    /**
     * 删除购物车中的某一个项
     *
     * @param skuId 购物车一项的唯一标识id
     */
    BackMsg<String> deleteCartItem(Integer skuId);


    /**
     * 清空购物车中所有商品
     */
    BackMsg<String> cleanCartAll();

    /**
     * 将未登录时用户购物车中的购物车信息合并到购物车数据库中保存
     * <p>用于登录操作时合并</p>
     */
    void MergeNoLoginCacheToCart();


}
