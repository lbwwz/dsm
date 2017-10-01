package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;
import com.dsm.model.order.OrderCheckInfo;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 */
public interface IOrderService {

    /**
     * checkOrder创建订单结算信息（使用redis 缓存缓存结算的商品信息）
     * <p>当订单结算信息(缓存的结算info)不存在时做强制库存校验</p>
     * @return 返回校验情况
     */
    BackMsg<String> checkOrderInfo();


    /**
     * 获取结算页的结算信息
     * @param items 结算元素String
     * @param addressId 选中的运送地址ID，若为空，则默认选中为默认地址
     * @return 结算页结算信息
     */
    BackMsg<OrderCheckInfo> getOrderCheckInfo(String items, Integer addressId);
}
