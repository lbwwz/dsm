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
     * 添加商品到购物车
     * @param skuId 商品sku
     * @param count 商品数量
     * @param cookieEnabled
     * @return
     */
    BackMsg<String> addToCart(Integer skuId, Integer count, boolean cookieEnabled);
}
