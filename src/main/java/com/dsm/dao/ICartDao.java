package com.dsm.dao;

import com.dsm.model.cart.ShoppingCartItem;
import com.dsm.model.cart.ShoppingCartItemPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/15
 *
 * @author : Lbwwz
 */
@Repository
public interface ICartDao {
    /**
     * 添加商品到购物车
     *
     * @param shoppingCartItemPO 持久化购物车子项数据对象
     */
    Integer addToCart(ShoppingCartItemPO shoppingCartItemPO);

    /**
     * 更新购物车中的商品信息
     *
     * @param shoppingCartItemPO 持久化购物车子项数据对象
     */
    Integer updateCart(ShoppingCartItemPO shoppingCartItemPO);

    /**
     * 删除购物车中的某一个子项
     *
     * @param cartItemId 购物车一项的唯一标识id
     */
    Integer deleteCartItem(int cartItemId);


    /**
     * 清空购物车中所有商品
     *
     * @param userId 用户ID
     */
    Integer cleanCartAll(int userId);

    /**
     * 根据cartItemId 获取购物车中的商品信息
     *
     * @param cartItemId 购物车一项的唯一标识id
     * @return 购物车一条子项
     */
    ShoppingCartItem getShoppingCartItem(int cartItemId);


    /**
     * 根据商品的skuId和用户ID 获取购物车项
     */
    ShoppingCartItem getShoppingCartItemBySkuId(@Param("userId")int userId, @Param("skuId")int skuId);


    /**
     * 根据店铺来查购物车列表
     *
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 购物车商品信息列表
     */
    List<ShoppingCartItem> getShoppingCartItemByShopId(@Param("userId") int userId, @Param("shopId") int shopId);


    /**
     * 查询某个用户的购物车信息
     *
     * @param userId 用户ID
     * @return 购物车所有子项
     */
    List<ShoppingCartItem> getShoppingCartInfoAll(int userId);


}
