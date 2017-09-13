package com.dsm.controller.balance;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.annotation.RepeatSubmitCheck;
import com.dsm.common.utils.CookieUtil;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.controller.common.BaseController;
import com.dsm.model.BackMsg;
import com.dsm.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;


    /**
     * 请求跳转购物车页面
     */
    @RequestMapping("")
    public String cart(Map<String, Object> m) {

        /**
         * 这里要对非登陆而且禁用cookie的用户购物车做检验，
         */
        if (SessionToolUtils.checkLogin() == 0 && !CookieUtil.checkCookieAbleToUsed()) {
            return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/showLogin";
        }
        m.put("cartInfo", cartService.getMyShoppingCart());

        return "/cart";

    }

    /**
     * 添加或减少商品到购物车
     *
     * @param skuId          skuId
     * @param count          商品数量
     * @param returnWithData 是否返回购物车商品数据信息
     */
    @RepeatSubmitCheck(successCheck = false)
    @ResponseBody
    @RequestMapping("addOrMinusToCart")
    public BackMsg<String> addOrMinusToCart(Integer skuId, @RequestParam(defaultValue = "1") Integer count,
                                            @RequestParam(defaultValue = "false") Integer returnWithData) {

        if (SessionToolUtils.checkLogin() == 0 && !CookieUtil.checkCookieAbleToUsed()) {
            return new BackMsg<>(DsmConcepts.NEED_REDIRECT, "/showLogin", "禁用浏览器cookie的未登录用户需要跳转登录页面");
        }
        count = count == null ? 1 : count;
        BackMsg<String> msg = cartService.addOrMinusToCart(skuId, count);
        if (msg.getError() == DsmConcepts.CORRECT && returnWithData == 1) {
            //操作成功
            msg.setData(JSONObject.toJSONString(cartService.getMyShoppingCart()));

        }
        return msg;
    }

    /**
     * 根据输入的数量改变购物车中商品数量
     *
     * @param skuId        商品skuId
     * @param changedCount 变更的商品数量
     * @return
     */
    @RepeatSubmitCheck(successCheck = false)
    @ResponseBody
    @RequestMapping("changeNumInCart")
    public BackMsg<String> changeNumInCart(Integer skuId, @RequestParam(defaultValue = "1") Integer changedCount) {
        if (changedCount == null || changedCount < 1) {
            return new BackMsg<>(DsmConcepts.ERROR, null, "改变的商品数量异常！");
        }
        if (SessionToolUtils.checkLogin() == 0 && !CookieUtil.checkCookieAbleToUsed()) {
            return new BackMsg<>(DsmConcepts.NEED_REDIRECT, "/showLogin", "禁用浏览器cookie的未登录用户需要跳转登录页面");
        }
        BackMsg<String> msg = cartService.changeNumInCart(skuId, changedCount);
        if (msg.getError() == DsmConcepts.CORRECT || msg.getError() == DsmConcepts.PRODUCT_NO_STOCK) {
            //操作成功
            msg.setData(JSONObject.toJSONString(cartService.getMyShoppingCart()));

        }
        return msg;
    }


    /**
     * 选中功能变更选中状态
     *
     * @param id   skuId or shopId
     * @param type 选中的类型（枚举）：sku；shop；all
     */
    @ResponseBody
    @RequestMapping("changeSelected")
    public BackMsg<String> changeSelected(Integer id, @RequestParam(defaultValue = "sku") String type, int isSelected) {
        BackMsg<String> msg = cartService.changeItemsSelected(id, isSelected, type);
        if (msg.getError() == DsmConcepts.CORRECT) {
            //操作成功
            msg.setData(JSONObject.toJSONString(cartService.getMyShoppingCart()));
        }
        return msg;
    }

    /**
     * 选中功能变更选中状态
     *
     * @param skuId          skuId
     * @param returnWithData 是否返回购物车商品数据信息
     */
    @ResponseBody
    @RequestMapping("deleteItem")
    public BackMsg<String> deleteItem(Integer skuId, @RequestParam(defaultValue = "0") Integer returnWithData) {
        BackMsg<String> msg = cartService.deleteCartItem(skuId);
        if (msg.getError() == DsmConcepts.CORRECT && returnWithData == 1) {
            //操作成功
            msg.setData(JSONObject.toJSONString(cartService.getMyShoppingCart()));
        }
        return msg;
    }

    /**
     * 清空购物车
     */
    @ResponseBody
    @RequestMapping("cleanAll")
    public BackMsg<String> cleanAll() {
        return cartService.cleanCartAll();
    }

}
