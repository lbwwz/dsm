package com.dsm.controller.balance;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.annotation.RepeatSubmitCheck;
import com.dsm.controller.common.BaseController;
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
public class CartController extends BaseController{

    @Autowired
    private ICartService cartService;


    /**
     * 请求跳转购物车页面
     */
    @RequestMapping("")
    public String cart(Map<String,Object> m){

        m.put("cartInfo",cartService.getMyShoppingCart());

        return "/cart";

    }

    /**
     * 添加或减少商品到购物车
     * @param skuId skuId
     * @param count 商品数量
     * @param returnWithData 是否返回购物车商品信息
     * @param cookieEnabled 浏览器是否禁用cookie,若禁用，则引到用户登录
     * @return
     */
    @RepeatSubmitCheck(successCheck = false)
    @ResponseBody
    @RequestMapping("addOrMinusToCart")
    public BackMsg<String> addOrMinusToCart(Integer skuId,
                                            @RequestParam(defaultValue = "1") Integer count,
                                            @RequestParam(defaultValue = "0") Integer returnWithData,
                                            boolean cookieEnabled){
        count = count== null?1:count;
        BackMsg<String> msg = cartService.addOrMinusToCart(skuId,count,cookieEnabled);
        if(msg.getError() == 0 && returnWithData == 1){
            //操作成功
            msg.setData(JSONObject.toJSONString(cartService.getMyShoppingCart()));

        }
        return msg;
    }


    /**
     *
     * @param id skuId or shopId
     * @param type 选中的类型（枚举）：sku；shop；all
     * @return
     */
    @ResponseBody
    @RequestMapping("changeSelected")
    public BackMsg<String> changeSelected(Integer id,@RequestParam(defaultValue = "sku")String type,int isSelected){
        BackMsg<String> msg = cartService.changeItemsSelected(id,isSelected,type);


        return msg;
    }




}
