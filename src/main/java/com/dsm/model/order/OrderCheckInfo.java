package com.dsm.model.order;

import com.dsm.model.address.ShippingAddress;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 *         订单结算清单页面的相关信息(对应confirm_order页面)
 */
public class OrderCheckInfo{

    //收获地址信息地址信息
    public List<ShippingAddress> addressList;

    List<OrderPackage> orderPackages;


}
