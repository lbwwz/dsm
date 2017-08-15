package com.dsm.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.utils.CookieUtil;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.controller.common.RequestResponseContext;
import com.dsm.dao.ICartDao;
import com.dsm.dao.IProductSkuDao;
import com.dsm.model.BackMsg;
import com.dsm.model.cart.CartPackage;
import com.dsm.model.cart.ShoppingCart;
import com.dsm.model.cart.ShoppingCartItem;
import com.dsm.model.cart.ShoppingCartItemPO;
import com.dsm.model.product.ProductSkuItem;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.ICartService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

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

    //返回更新的商品信息
    @Override
    public BackMsg<String> addOrMinusToCart(Integer skuId, int changeCount, boolean cookieEnabled) {

        BackMsg<String> operateCartMsg = operateAddOrMinusToCart(skuId, changeCount, cookieEnabled);
        if (operateCartMsg.getError() == DsmConcepts.CORRECT) {

        } else if (operateCartMsg.getError() == DsmConcepts.NEED_REDIRECT) {//未登录需要跳转登录页

        } else {//发生错误，输出错误信息


        }
        return null;

    }

    @Override
    public ShoppingCart getMyShoppingCart() {
        //校验是否登录
        User user = SessionToolUtils.getUser();
//        String

        List<ShoppingCartItem> cartItemList = null;
        if (user != null) {
            cartItemList = getCartItemListFromCache("cart_" + user.getId());
            if(cartItemList == null){
                //如果不存在，从数据库中读取一次
                cartItemList = cartDao.getShoppingCartInfo(user.getId());
                setCartItemListToCache("cart_"+user.getId(),cartItemList);
            }
        } else {
            //未登录用户使用用户唯一标志符查找缓存中的购物车信息
            String userKey = CookieUtil.getCookieByName(RequestResponseContext.getRequest(), DsmConcepts.DSM_USER_KEY);

            if (StringUtils.isBlank(userKey)) {
                //标志key不存在，设置唯一标志key
                CookieUtil.addCookie(RequestResponseContext.getResponse(),
                        DsmConcepts.DSM_USER_KEY, UUID.randomUUID().toString(), DsmConcepts.DAY, true);
            } else {
                cartItemList = getCartItemListFromCache("cart_" + userKey);
            }
        }

        ShoppingCart cartInfo = null;
        if (cartItemList != null) {
            //这里进行购物车信息的封装和计算处理
            cartInfo = arrangeCartInfo(cartItemList);
        }
        return cartInfo;

    }

    /**
     * 从缓存中获取购物车数据信息
     * @param cartKey 缓存key
     * @return 购物车子项列表信息
     */
    private List<ShoppingCartItem> getCartItemListFromCache(String cartKey) {
        if(redisService.exists(cartKey)){
            Map<String, String> m = redisService.getHsetAll(cartKey);

            List<ShoppingCartItem> cartItemList = new ArrayList<>();
            for (Map.Entry<String, String> entry : m.entrySet()) {
                cartItemList.add(JSON.parseObject(entry.getValue(), ShoppingCartItem.class));
            }
            return cartItemList;
        }
        return null;
    }

    private boolean setCartItemListToCache(String cartKey,List<ShoppingCartItem> cartItemList){
        Map<String, String> m = new HashMap<>();
        for(ShoppingCartItem item : cartItemList){
            m.put(item.getSkuId()+"",JSONObject.toJSONString(item));
        }
        return redisService.setHmset(cartKey,m);
    }

    /**
     * 整理购物信息
     *
     * @param cartItemList 待处理的购物车元数据列表
     * @return 购物车数据对象
     */
    private ShoppingCart arrangeCartInfo(List<ShoppingCartItem> cartItemList) {

        Map<String, List<ShoppingCartItem>> map = new HashMap<>();

        //日期新的排在前面
        Collections.sort(cartItemList, (o1, o2) -> o1.getCreateTime().compareTo(o2.getCreateTime()));
        List<ShoppingCartItem> tempList;
        for (ShoppingCartItem item : cartItemList) {
            tempList = map.get(item.getShopId() + "");
            if (tempList != null) {
                tempList.add(item);
            } else {
                tempList = new ArrayList<>();
                tempList.add(item);
                map.put(item.getShopId() + "", tempList);
            }
        }
        ShoppingCart cartInfo = new ShoppingCart();

        //设置信息
        Integer tempShopId;
        String tempShopName;
        for (Map.Entry<String, List<ShoppingCartItem>> entry : map.entrySet()) {
            tempShopId = entry.getValue().get(0).getShopId();
            tempShopName = entry.getValue().get(0).getShopName();
            cartInfo.addCartPackage(new CartPackage(tempShopId, tempShopName, entry.getValue()));
        }
        //计算商品总价
        cartInfo.setTotalPrice(new BigDecimal(123));

        return cartInfo;
    }


    private BackMsg<String> operateAddOrMinusToCart(Integer skuId, int changeCount, boolean cookieEnabled) {
        if (changeCount == 0 || skuId == null) {
            return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
        }

        User user = SessionToolUtils.getUser();
        String msg;
        int errorCode;
        if (!cookieEnabled && user == null) {
            return new BackMsg<>(DsmConcepts.NEED_REDIRECT, "/showLogin", "禁用浏览器cookie的未登录用户需要跳转登录页面");

        } else if (user != null) {     //登录的用户处理操作

            //1.查询校验商品信息（是否存在？库存是否充足）
            ProductSkuItem thisSkuItem = productSkuDao.getProductSkuItem(skuId);
            if (thisSkuItem == null) {
                return new BackMsg<>(DsmConcepts.WARRING, null, "当前商品不存在!");
            }

            if (thisSkuItem.getQuantity() > changeCount) {
                try {
                    //2.查询缓存中该用户对应的购物车信息，若存在则更新，若不存在，则添加
                    String cartItemStr = redisService.getHSet("cart_" + user.getId(), skuId + "");
                    ShoppingCartItem cartItem = JSONObject.parseObject(cartItemStr, ShoppingCartItem.class);

                    ShoppingCartItemPO shoppingCartItemPO;

                    if (cartItem == null) {   // 添加操作

                        if (changeCount < 0) {
                            return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
                        }
                        shoppingCartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                                skuId, changeCount, 1);
                        cartDao.addToCart(shoppingCartItemPO);

                    } else {                  //更新操作
                        shoppingCartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                                skuId, cartItem.getCartItemNum() + changeCount, cartItem.getIsSelected());
                        cartDao.updateCart(shoppingCartItemPO);
                    }

                    //3.查询最新的信息，并更新redis缓存
                    ShoppingCartItem newCartItem = cartDao.getShoppingCartItem(cartItem == null ? shoppingCartItemPO.getCartItemId() : cartItem.getCartItemId());
                    redisService.setHSet("cart_" + user.getId(), skuId + "", JSONObject.toJSONString(newCartItem));
                    redisService.expire("cart_" + user.getId(), DsmConcepts.DAY);

                    errorCode = DsmConcepts.CORRECT;
                    msg = "购物车商品修改成功";
                } catch (Exception ex) {
                    errorCode = DsmConcepts.ERROR;
                    msg = "购物车商品修改失败，请稍后重试！";
                }
            } else {
                errorCode = DsmConcepts.PRODUCT_NO_STOCK;
                msg = "商品库存不足！";
            }
        } else { //能使用浏览器cookie的未登录用户使用用户唯一键cookie，用作为标志以获取缓存中的购物车信息
            HttpServletRequest request = RequestResponseContext.getRequest();
            HttpServletResponse response = RequestResponseContext.getResponse();
            String unLoginUserKey = CookieUtil.getCookieByName(request, DsmConcepts.DSM_USER_KEY);
            if (StringUtils.isBlank(unLoginUserKey)) {
                //user_key 为空
                unLoginUserKey = UUID.randomUUID().toString();
                CookieUtil.addCookie(response, DsmConcepts.DSM_USER_KEY, unLoginUserKey, DsmConcepts.DAY, true);
                //使用用户唯一键设置购物车缓存数据

            } else {
                //更新redis缓存

            }

            //检查唯一标识的cookie
            if (true) {
                errorCode = DsmConcepts.CORRECT;
                msg = "购物车商品修改成功";
            } else {
                errorCode = DsmConcepts.ERROR;
                msg = "购物车商品修改失败，请稍后重试！";
            }
        }
        return new BackMsg<>(errorCode, null, msg);
    }


    /**
     * 将操作的购物车修改信息重写到cookie中（用户未登录）
     *
     * @param skuId       skuId
     * @param changeCount 商品变更数量
     */
    private boolean rewriteCartCookie(String skuId, int changeCount) {
        HttpServletRequest request = RequestResponseContext.getRequest();
        HttpServletResponse response = RequestResponseContext.getResponse();
        //获取cookie中的信息
        String shoppingCartStr = CookieUtil.getCookieByName(request, DsmConcepts.DSM_SHOPPING_CART);
        try {
            //合并cookie购物车商品信息
            shoppingCartStr = mergeCartItem(shoppingCartStr, skuId, changeCount);
            //重新写入购物车cookie保存7天
            CookieUtil.addCookie(response, DsmConcepts.DSM_SHOPPING_CART, shoppingCartStr, 7 * DsmConcepts.DAY, true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 合并购物车，cookie和修改操作的信息（cookie信息格式[skuId:数量;...]：103221：1;123221:12; ...）
     *
     * @param cookieCartStr cookie信息
     * @param skuId         操作的skuId
     * @param changeCount   变更数量
     * @return 合并后的信息串
     * @throws Exception cookie可能的格式操作错误（一般不存在，只是做异常处理）
     */
    private String mergeCartItem(String cookieCartStr, String skuId, int changeCount) throws Exception {
        StringBuilder builder = new StringBuilder();
        //cookie为空，设置新cookie信息
        if (cookieCartStr == null) {
            return builder.append(skuId).append(":").append(changeCount).toString();
        }
        Map<String, Integer> m = new HashMap<>();
        String[] items = cookieCartStr.split(";");

        try {
            String[] temp;
            for (String item : items) {
                temp = item.split(":");
                m.put(temp[0], Integer.parseInt(temp[1]));
            }

            Integer originCount;
            if ((originCount = m.get(skuId)) != null) {
                m.put(skuId, originCount + changeCount);
                for (Map.Entry<String, Integer> entry : m.entrySet()) {
                    builder.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
                }
                builder.deleteCharAt(builder.length() - 1);
            } else {
                builder.append(cookieCartStr).append(";").append(skuId).append(":").append(changeCount);
            }
            return builder.toString();
        } catch (Exception ex) {
            logger.error("购物车cookie操作添加失败,cookie为：{}", cookieCartStr);
            throw new Exception("购物车cookie操作添加失败");
        }
    }

}
