package com.dsm.controller.balance;

import com.dsm.model.BackMsg;
import com.dsm.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/15
 *
 * @author : Lbwwz
 *         <p/>
 *         购物车相关操作的controller
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService cartService;


    /**
     * 请求跳转购物车页面
     */
    @RequestMapping("cart")
    public String cart(Map<String,Object> m){

        m.put("cartInfo",cartService.getMyShoppingCart());

        return "cart";

    }

    /**
     * 将商品加入购物车
     * @param skuId skuId
     * @param count 商品数量
     * @param cookieEnabled 浏览器是否禁用cookie,若禁用，则引到用户登录
     * @return
     */
    @ResponseBody
    @RequestMapping("cart/addToCart")
    public BackMsg<String> addToCart(Integer skuId,@RequestParam(defaultValue = "1") Integer count,boolean cookieEnabled){

        BackMsg<String> msg = cartService.addOrMinusToCart(skuId,count,cookieEnabled);
        return null;

    }


}
