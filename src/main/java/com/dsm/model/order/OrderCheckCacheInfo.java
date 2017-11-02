package com.dsm.model.order;

import com.dsm.model.address.ShippingAddress;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/10/26
 *
 * @author : Lbwwz
 * <p>
 *     用户缓存结算页操作的相关的信息
 * </p>
 */
public class OrderCheckCacheInfo {

    //选中的物流地址
    private ShippingAddress address;

    private List<OrderSkuCacheItem> orderSkuItemList;


    public List<OrderSkuCacheItem> getOrderSkuItemList() {
        return orderSkuItemList;
    }

    public void setOrderSkuItemList(List<OrderSkuCacheItem> orderSkuItemList) {
        this.orderSkuItemList = orderSkuItemList;
    }

    public ShippingAddress getAddress() {
        return address;
    }

    public void setAddress(ShippingAddress address) {
        this.address = address;
    }

    public OrderCheckCacheInfo(List<OrderSkuCacheItem> orderSkuItemList) {
        this.orderSkuItemList = orderSkuItemList;
    }
}
