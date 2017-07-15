package com.dsm.service.impls;

import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.utils.CookieUtil;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.controller.common.RequestResponseContext;
import com.dsm.dao.ICartDao;
import com.dsm.dao.IProductSkuDao;
import com.dsm.model.BackMsg;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/15
 *
 * @author : Lbwwz
 */
@Service("ICartService")
public class CartServiceImpl implements ICartService {

    @Resource
    private ICartDao cartDao;

    @Resource
    private IProductSkuDao productSkuDao;

    @Resource
    private IRedisService redisService;

    public static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public BackMsg<String> addOrMinusToCart(String skuId, int changeCount, boolean cookieEnabled) {
        if(changeCount == 0){
            return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
        }

        User user = SessionToolUtils.getUser();
        String msg;
        int errorCode;
        if (!cookieEnabled && user == null) {
            return new BackMsg<>(DsmConcepts.NEED_REDIRECT, "/showLogin", "禁用浏览器cookie的未登录用户需要跳转登录页面");
        }else if(user != null){//登录的用户
            //先校验该商品库存、若库存不足，则添加失败
            int skuQuantity = productSkuDao.getSkuQuantity(Integer.parseInt(skuId));
            if(skuQuantity>0){
                //库存充足，保存更改到购物车信息中
                try{
                    //先查询对应的购物车信息，若存在则更新，若不存在，则添加
                    cartDao.addToCart();
                    errorCode = DsmConcepts.CORRECT;
                    msg = "购物车商品修改成功";
                }catch (Exception ex){
                    logger.error("添加到购物车异常");
                    errorCode = DsmConcepts.ERROR;
                    msg = "购物车商品修改失败，请稍后重试！";
                }
            }else{
                errorCode = DsmConcepts.PRODUCT_NO_STOCK;
                msg = "商品库存不足！";
            }
        }else{ //能使用浏览器cookie的未登录用户将信息临时存放在cookie中
            boolean flag = rewriteCartCookie(skuId,changeCount);
            if(flag){
                errorCode = DsmConcepts.CORRECT;
                msg = "购物车商品修改成功";
            }else{
                errorCode = DsmConcepts.ERROR;
                msg = "购物车商品修改失败，请稍后重试！";
            }
        }
        return new BackMsg<>(errorCode, null, msg);
    }


    /**
     * 将操作的购物车修改信息重写到cookie中（用户未登录）
     * @param skuId skuId
     * @param changeCount 商品变更数量
     * @return
     */
    private boolean rewriteCartCookie(String skuId,int changeCount){
        HttpServletRequest request = RequestResponseContext.getRequest();
        HttpServletResponse response = RequestResponseContext.getResponse();
        //获取cookie中的信息
        String shoppingCartStr = CookieUtil.getCookieByName(request,DsmConcepts.DSM_SHOPPING_CART);
        try{
            //合并cookie购物车商品信息
            shoppingCartStr =  mergeCartItem(shoppingCartStr,skuId,changeCount);
            //写入购物车cookie保存7天
            CookieUtil.addCookie(response,DsmConcepts.DSM_SHOPPING_CART,shoppingCartStr,7*DsmConcepts.DAY,true);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * 合并购物车，cookie和修改操作的信息
     * @param cookieCartStr cookie信息
     * @param skuId 操作的skuId
     * @param changeCount 变更数量
     * @return 合并后的信息串
     * @throws Exception cookie可能的格式操作错误（一般不存在，只是做异常处理）
     */
    private String mergeCartItem(String cookieCartStr,String skuId,int changeCount)throws Exception{
        StringBuilder builder = new StringBuilder();
        if(cookieCartStr== null){
            return builder.append(skuId).append(":").append(changeCount).toString();
        }
        Map<String,Integer> m = new HashMap<>();
        String[] items = cookieCartStr.split(";");

        try{
            String[] temp;
            for(String  item : items){
                temp = item.split(":");
                m.put(temp[0],Integer.parseInt(temp[1]));
            }

            Integer originCount;
            if((originCount = m.get(skuId))!= null){
                m.put(skuId,originCount+changeCount);
                for(Map.Entry<String,Integer> entry : m.entrySet()){
                    builder.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
                }
                builder.deleteCharAt(builder.length()-1);
            }else{
                builder.append(cookieCartStr).append(";").append(skuId).append(":").append(changeCount);
            }
            return builder.toString();
        }catch(Exception ex){
            logger.error("购物车cookie操作添加失败,cookie为：{}",cookieCartStr);
            throw new Exception("购物车cookie操作添加失败");
        }
    }

}
