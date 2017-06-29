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
    public BackMsg<String> addToCart(Integer skuId, Integer count, boolean cookieEnabled) {



        User user = SessionToolUtils.getUser();
        if (!cookieEnabled && user == null) {
            return new BackMsg<>(DsmConcepts.NEED_REDIRECT, "/showLogin", "禁用浏览器cookie的未登录用户需要跳转登录页面");
        }else if(user != null){//登录的用户
            //先校验该商品库存、若库存不足，则添加失败
            int skuQuantity = productSkuDao.getSkuQuantity(skuId);

            if(skuQuantity>0){
                //库存充足，保存更改到购物车信息中
                try{
                    Integer num = cartDao.addToCart();

                }catch (Exception ex){
                    logger.error("添加到购物车异常");
                    return new BackMsg<>(DsmConcepts.ERROR, "", "购物车商品添加失败，请稍后重试");
                }

            }

        }else{ //能使用浏览器cookie的未登录用户将信息临时存放在cookie中
            HttpServletRequest request = RequestResponseContext.getRequest();
            HttpServletResponse response = RequestResponseContext.getResponse();

            String shoppingCartStr = CookieUtil.getCookieByName(request,"shoppingCart");


        }
        return null;
    }
}
