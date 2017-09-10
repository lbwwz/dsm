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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

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

    private final String noLogin_cart_prov = "cartCache_";
    private final String login_cart_prov = "cart_";


    @Override
    public ShoppingCart getMyShoppingCart() {
        //校验是否登录
        User user = SessionToolUtils.getUser();
//        String

        List<ShoppingCartItem> cartItemList = null;
        if (user != null) {
            cartItemList = getCartItemListFromCache(login_cart_prov + user.getId());
            if (cartItemList == null) {
                //如果不存在，从数据库中读取一次
                cartItemList = cartDao.getShoppingCartInfoAll(user.getId());
                setCartItemListToCache(login_cart_prov + user.getId(), cartItemList);
            }
        } else {
            //未登录用户使用用户唯一标志符查找缓存中的购物车信息
            String userKey = CookieUtil.getCookieByName(RequestResponseContext.getRequest(), DsmConcepts.DSM_USER_KEY);

            if (StringUtils.isBlank(userKey)) {
                //标志key不存在，设置唯一标志key
                CookieUtil.addCookie(RequestResponseContext.getResponse(),
                        DsmConcepts.DSM_USER_KEY, UUID.randomUUID().toString(), DsmConcepts.DAY, true);
            } else {
                cartItemList = getCartItemListFromCache(noLogin_cart_prov + userKey);
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
     *
     * @param cartKey 缓存key
     * @return 购物车子项列表信息
     */
    private List<ShoppingCartItem> getCartItemListFromCache(String cartKey) {
        if (redisService.exists(cartKey)) {
            List<String> List = redisService.hvals(cartKey);
            redisService.expire(cartKey, DsmConcepts.DAY);
            List<ShoppingCartItem> cartItemList = new ArrayList<>();
            for (String str : List) {
                cartItemList.add(JSON.parseObject(str, ShoppingCartItem.class));
            }
            return cartItemList;
        }
        return null;
    }

    /**
     * 设置购物车信息到缓存中
     *
     * @param cartKey redis中购物车项的缓存的key
     * @param cartItemList 购物车条目信息列表
     */
    private boolean setCartItemListToCache(String cartKey, List<ShoppingCartItem> cartItemList) {
        Map<String, ShoppingCartItem> m = new HashMap<>();
        for (ShoppingCartItem item : cartItemList) {
            m.put(item.getSkuId() + "", item);
        }
        if (redisService.setHmset(cartKey, m)) {
            redisService.expire(cartKey, DsmConcepts.DAY);
            return true;
        }
        return false;
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
        BigDecimal totalAmount = new BigDecimal(0);
        int totalNum = 0;
        for (Map.Entry<String, List<ShoppingCartItem>> entry : map.entrySet()) {

            tempShopId = entry.getValue().get(0).getShopId();
            tempShopName = entry.getValue().get(0).getShopName();
            cartInfo.addCartPackage(new CartPackage(tempShopId, tempShopName, entry.getValue()));
            for (ShoppingCartItem item : entry.getValue()) {
                if (item.getIsSelected() == 1) {
                    //计算商品总价
                    totalAmount = totalAmount.add(item.getSkuPrice().multiply(BigDecimal.valueOf(item.getCartItemNum())));
                    //计算选中商品总件数
                    totalNum += item.getCartItemNum();
                }

            }
        }
        cartInfo.setTotalPrice(totalAmount);
        cartInfo.setSelectTotalNum(totalNum);

        return cartInfo;
    }


    @Override
    public BackMsg<String> addOrMinusToCart(Integer skuId, int changeCount, boolean cookieEnabled) {
        if (changeCount == 0 || skuId == null) {
            return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
        }
        String msg;
        int errorCode;
        /*
         * 1.查询校验商品信息（是否存在？库存是否充足）
         */
        ProductSkuItem thisSkuItem = productSkuDao.getProductSkuItem(skuId);
        if (thisSkuItem == null) {
            return new BackMsg<>(DsmConcepts.WARRING, null, "当前商品不存在!");
        }
        if (thisSkuItem.getQuantity() > changeCount) {
            User user = SessionToolUtils.getUser();
            if (!cookieEnabled && user == null) {   //不能使用cookie的未登录用户
                return new BackMsg<>(DsmConcepts.NEED_REDIRECT, "/showLogin", "禁用浏览器cookie的未登录用户需要跳转登录页面");

            } else {
                try {
                    String redisCartKey;
                    if (user != null) {     //登录的用户
                        redisCartKey = login_cart_prov + user.getId();
                    } else { //未登录用户
                        HttpServletRequest request = RequestResponseContext.getRequest();
                        HttpServletResponse response = RequestResponseContext.getResponse();
                        String unLoginUserKey = CookieUtil.getCookieByName(request, DsmConcepts.DSM_USER_KEY);
                        if (StringUtils.isBlank(unLoginUserKey)) {
                            //user_key 为空，则生成并设置到cookie中
                            unLoginUserKey = UUID.randomUUID().toString();
                            CookieUtil.addCookie(response, DsmConcepts.DSM_USER_KEY, unLoginUserKey, DsmConcepts.DAY, true);
                        }
                        redisCartKey = noLogin_cart_prov + unLoginUserKey;
                    }

                    /*
                     *  2.查询缓存中该用户对应的购物车信息，若存在则更新，若不存在，则新增
                     */
                    ShoppingCartItem cartItem = redisService.getHSetAsObject(redisCartKey, skuId + "", ShoppingCartItem.class);
                    if (cartItem == null && changeCount < 0) {
                        return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
                    } else {
                        if (cartItem != null && cartItem.getCartItemNum() + changeCount <= 0) {
                            throw new Exception("修改操作不能将商品数量变为0！");
                        }
                        if (user != null) {
                            ShoppingCartItemPO shoppingCartItemPO;
                            int opFlag;
                            if (cartItem == null) {
                                shoppingCartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                                        skuId, changeCount, 1);
                                opFlag = cartDao.addToCart(shoppingCartItemPO);
                            } else {

                                shoppingCartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                                        skuId, cartItem.getCartItemNum() + changeCount, cartItem.getIsSelected());
                                opFlag = cartDao.updateCart(shoppingCartItemPO);
                            }
                            if (opFlag <= 0) {
                                throw new Exception();
                            }

                            cartItem = cartDao.getShoppingCartItem(cartItem == null ? shoppingCartItemPO.getCartItemId() : cartItem.getCartItemId());
                        } else {
                            if (cartItem == null) {
                                cartItem = new ShoppingCartItem();
                                BeanUtils.copyProperties(thisSkuItem, cartItem);
                                cartItem.setIsSelected(1);
                                cartItem.setCartItemNum(changeCount);
                            } else {
                                cartItem.setCartItemNum(cartItem.getCartItemNum() + changeCount);
                            }
                        }
                        //3.将最新的信息，更新到redis缓存
                        redisService.setHSet(redisCartKey, skuId + "", cartItem);
                        redisService.expire(redisCartKey, DsmConcepts.DAY);
                    }
                    errorCode = DsmConcepts.CORRECT;
                    msg = "购物车商品修改成功";
                } catch (Exception ex) {
                    String errorMsgStr = ex.getMessage();
                    errorCode = DsmConcepts.ERROR;
                    msg = StringUtils.isNoneBlank(errorMsgStr) ? errorMsgStr : "购物车商品修改失败，请稍后重试！";
                }
            }
        } else {
            errorCode = DsmConcepts.PRODUCT_NO_STOCK;
            msg = "商品库存不足！";
        }
        return new BackMsg<>(errorCode, null, msg);
    }


    @Override
    public BackMsg<String> changeItemsSelected(Integer id, int isSelected, String type) {

        User user = SessionToolUtils.getUser();

        int errorNum;
        String msg;
        try {
            if (user != null) {
                /**
                 * 1.构造用于更新操作的购物车元数据对象
                 */
                ShoppingCartItemPO scPO = buildUpdateSciPo(user, id, isSelected, type);
                Integer i = cartDao.updateCart(scPO);
                if (i < 1) {
                    throw new Exception("无效的购物车更新操作：" + type + "=" + id);
                }
            }
            /**
             * 2.更新成功，查询更新后的对应的购物车信息，并更新到缓存中
             */
            updateShoppingCartInfoToCache(user, id, isSelected, type);

        } catch (Exception ex) {
            String errorMsgStr = ex.getMessage();
            errorNum = DsmConcepts.ERROR;
            msg = StringUtils.isNoneBlank(errorMsgStr) ? errorMsgStr : "购物车商品修改失败，请稍后重试！";
        }
        return null;

    }

    /**
     * 构造用于更新操作的购物车元数据对象
     *
     * @param user       用户信息，只有登录用户才有该信息
     * @param id         根据type的不同表示不同的含义，可以是skuId或者shopId
     * @param isSelected 选中状态，0表示不选中，1表示选中
     * @param type       类型，可以是sku shop 和 all
     * @return {@link ShoppingCartItemPO}
     */
    private ShoppingCartItemPO buildUpdateSciPo(User user, Integer id, int isSelected, String type) {
        ShoppingCartItemPO sciPO = new ShoppingCartItemPO();
        switch (type) {
            case "sku"://选中单品
                sciPO.setUserId(user.getId());
                sciPO.setSkuId(id);
                sciPO.setIsSelected(isSelected);
                break;
            case "shop":// 选中店铺所有单品
                sciPO.setUserId(user.getId());
                sciPO.setShopId(id);
                sciPO.setIsSelected(isSelected);
                break;
            default: //全选
                sciPO.setUserId(user.getId());
                sciPO.setIsSelected(isSelected);
                break;
        }
        return sciPO;
    }

    /**
     * 更新缓存中的购物车信息
     * <p>
     *     这里只有用户信息不为空的时候才查询数据库更新，未登录用户直接更新信息
     * </p>
     *
     * @param user       用户信息，只有登录用户才有该信息
     * @param id         根据type的不同表示不同的含义，可以是skuId或者shopId
     * @param isSelected 选中状态，0表示不选中，1表示选中
     * @param type       类型，可以是sku shop 和 all
     */
    private void updateShoppingCartInfoToCache(User user, Integer id, int isSelected, String type) {
        if(user != null){
            if("sku".equals(type)) {//选中单品
                ShoppingCartItem item = cartDao.getShoppingCartItemBySkuId(user.getId(), id);
                redisService.setHSet(login_cart_prov + user.getId(), item.getSkuId() + "", item);
            }else {
                List<ShoppingCartItem> list;
                if ("shop".equals(type)) {// 选中店铺所有单品
                     list = cartDao.getShoppingCartItemByShopId(user.getId(), id);
                }else{
                    list = cartDao.getShoppingCartInfoAll(user.getId());
                }

                //遍历将信息更新到redis缓存中
                list.stream().forEach(cartItem -> {
                    cartItem.setIsSelected(isSelected);
                    redisService.setHSet(login_cart_prov + user.getId(), cartItem.getSkuId() + "", cartItem);
                });
            }
        }else{
            String unLoginUserKey = CookieUtil.getCookieByName(RequestResponseContext.getRequest(), DsmConcepts.DSM_USER_KEY);
            if("sku".equals(type)) {    //选中单品操作
                ShoppingCartItem shoppingCartItem = redisService.getHSetAsObject(noLogin_cart_prov + unLoginUserKey, id + "", ShoppingCartItem.class);
                shoppingCartItem.setIsSelected(isSelected);
                redisService.setHSet(noLogin_cart_prov + unLoginUserKey, shoppingCartItem.getSkuId() + "", shoppingCartItem);
            }else {     //  店铺商品多选和全部商品多选操作
                //获取购物车所有项
                List<String> list = redisService.hvals(noLogin_cart_prov + unLoginUserKey);

                //流操作
                Stream<ShoppingCartItem> stream = list.stream().map(s -> JSONObject.parseObject(s,ShoppingCartItem.class));
                if("shop".equals(type)) { //店铺全选操作
                    stream = stream.filter(item -> Objects.equals(item.getShopId(), id));
                }
                stream.forEach(afterItem->{
                    afterItem.setIsSelected(isSelected);
                    redisService.setHSet(noLogin_cart_prov + unLoginUserKey,afterItem.getSkuId()+"",afterItem);
                });
            }
        }

    }


}
