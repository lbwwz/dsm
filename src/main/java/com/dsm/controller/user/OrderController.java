package com.dsm.controller.user;

import com.dsm.model.BackMsg;
import com.dsm.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @RequestMapping("checkCartItem")
    public BackMsg checkOrder(){

        BackMsg<String> msg = orderService.checkCartItem();
        return null;
    }
}
