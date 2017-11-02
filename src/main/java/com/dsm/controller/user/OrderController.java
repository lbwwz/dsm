package com.dsm.controller.user;

import com.dsm.model.BackMsg;
import com.dsm.model.order.OrderCheckInfo;
import com.dsm.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 * <p>
 *     订单相关的操作（都需要登录操作）
 * </p>
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    /**
     * 检验结算商品信息（购物车缓存中选中的商品校验）
     * @return
     */
    @ResponseBody
    @RequestMapping("checkOrderInfo")
    public BackMsg checkOrderInfo(){

        BackMsg<String> backMsg = orderService.checkOrderInfo();
//        BackMsg<String> msg = orderService.checkCartItem();
        return backMsg;
    }

    /**
     * 订单结算页面
     * @param items 订单的确认商品信息
     * @param buyerFrom 核算页面从哪个页面跳转核算
     */
    @RequestMapping("confirm_order.htm")
    public String confirmOrder(Map<String,Object> m, String items, String buyerFrom){
        m.put("items",items);
        m.put("buyerFrom",buyerFrom);
        return "/order/confirm_order";

    }

    @RequestMapping(value = "getOrderCheckInfo",produces = {"text/html;charset=UTF-8"})
    public String getOrderCheckInfo(Map<String,Object> m,String items,Long addressId){
        BackMsg<OrderCheckInfo> backMsg = orderService.getOrderCheckInfo(items,addressId);

        m.put("orderCheckInfo",backMsg.getData());
        return "/order/orderCheckInfo";
    }
}
