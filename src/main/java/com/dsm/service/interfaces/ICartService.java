package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;

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
     * @param changeCount 变更数量商品数量
     * @param cookieEnabled
     * @return
     */
    BackMsg<String> addOrMinusToCart(String skuId, int changeCount, boolean cookieEnabled);
}
