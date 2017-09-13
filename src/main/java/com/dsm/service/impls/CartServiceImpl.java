package com.dsm.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.exception.CustomErrorMsgException;
import com.dsm.common.utils.CookieUtil;
import com.dsm.common.utils.SessionToolUtils;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
            String userKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);

            if (StringUtils.isBlank(userKey)) {
                //标志key不存在，设置唯一标志key
                CookieUtil.addCookie(DsmConcepts.DSM_USER_KEY, UUID.randomUUID().toString(), DsmConcepts.DAY, true);
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
     * @param cartKey      redis中购物车项的缓存的key
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
    public BackMsg<String> addOrMinusToCart(Integer skuId, int changeCount) {
        if (changeCount == 0 || skuId == null) {
            return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
        }
        return changeNumInCart(skuId, changeCount, null);
    }

    @Override
    public BackMsg<String> changeNumInCart(Integer skuId, Integer changedCount) {
        changedCount = (changedCount == null ? 1 : changedCount);
        if (skuId == null) {
            return new BackMsg<>(DsmConcepts.ERROR, null, "skuId不能为空！");
        }
        if (changedCount <= 0) {
            return new BackMsg<>(DsmConcepts.ERROR, null, "变更商品数量不能小于等于0！");
        }

        return changeNumInCart(skuId, null, changedCount);
    }

    /**
     * 具体的更新购物车商品数量的方法
     * <p>
     * changeCount存在优先使用changeCount，若change为空，才使用changedCount
     * </p>
     *
     * @param skuId        商品skuId
     * @param changeCount  商品修改的变化数量
     * @param changedCount 商品修改为数量
     */
    private BackMsg<String> changeNumInCart(int skuId, Integer changeCount, Integer changedCount) {

        ProductSkuItem thisSkuItem = productSkuDao.getProductSkuItem(skuId);
        if (thisSkuItem == null) {
            return new BackMsg<>(DsmConcepts.WARRING, null, "操作的该商品项不可用！");
        }
        int errorCode = DsmConcepts.CORRECT;
        String msg = null;
        try {
            User user = SessionToolUtils.getUser();
            String redisCartKey;
            if (user != null) {     //登录的用户
                redisCartKey = login_cart_prov + user.getId();
            } else { //未登录用户
                String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
                if (StringUtils.isBlank(unLoginUserKey)) {
                    //user_key 为空，则生成并设置到cookie中
                    unLoginUserKey = UUID.randomUUID().toString();
                    CookieUtil.addCookie(DsmConcepts.DSM_USER_KEY, unLoginUserKey, DsmConcepts.MONTH, true);
                }
                redisCartKey = noLogin_cart_prov + unLoginUserKey;
            }
            ShoppingCartItem cartItem = redisService.getHSetAsObject(redisCartKey, skuId + "", ShoppingCartItem.class);
//            if (cartItem == null && changeCount < 0) {
//                return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
//            }
            if (changeCount != null) {
                changedCount = changeCount + (cartItem == null ? 0 : cartItem.getCartItemNum());
            }
            if (thisSkuItem.getQuantity() < changedCount) {
                changedCount = thisSkuItem.getQuantity();
                errorCode = DsmConcepts.PRODUCT_NO_STOCK;
                msg = "最大库存不足！";
            }
                /*
                 *  2.查询缓存中该用户对应的购物车信息，若存在则更新，若不存在，则新增
                 */
            if (cartItem != null && changedCount <= 0) {
                //？可以更新为删除操作
                throw new CustomErrorMsgException("修改操作不能将商品数量变为0！");
            }
            if (user != null) {
                ShoppingCartItemPO shoppingCartItemPO;
                long opFlag;
                if (cartItem == null) {
                    shoppingCartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                            skuId, changedCount, 1);
                    opFlag = cartDao.addCartItem(shoppingCartItemPO);
                } else {
                    shoppingCartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                            skuId, changedCount, cartItem.getIsSelected());
                    opFlag = cartDao.updateCartItem(shoppingCartItemPO);
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
                }
                cartItem.setCartItemNum(changedCount);
            }
            //3.将最新的信息，更新到redis缓存
            redisService.setHSet(redisCartKey, skuId + "", cartItem);
            redisService.expire(redisCartKey, DsmConcepts.DAY);
            msg = (errorCode == DsmConcepts.CORRECT) ? "购物车商品修改成功" : msg;
        } catch (Exception ex) {
            errorCode = DsmConcepts.ERROR;
            msg = (ex instanceof CustomErrorMsgException) ? ex.getMessage() : "购物车商品修改失败，请稍后重试！";
        }
        return new BackMsg<>(errorCode, null, msg);
    }


    @Override
    public BackMsg<String> changeItemsSelected(Integer id, int isSelected, String type) {

        User user = SessionToolUtils.getUser();

        int errorCode;
        String msg;
        try {
            if (user != null) {
                /**
                 * 1.构造用于更新操作的购物车元数据对象
                 */
                ShoppingCartItemPO scPO = buildUpdateSciPo(user, id, isSelected, type);
                long i = cartDao.updateCartItem(scPO);
                if (i < 1) {
                    throw new CustomErrorMsgException("无效的购物车更新操作：" + type + "=" + id);
                }
            }
            /**
             * 2.更新成功，查询更新后的对应的购物车信息，并更新到缓存中
             */
            updateShoppingCartSelectedToCache(user, id, isSelected, type);
            errorCode = DsmConcepts.CORRECT;
            msg = "购物车商品修改成功！";
        } catch (Exception ex) {
            errorCode = DsmConcepts.ERROR;
            msg = (ex instanceof CustomErrorMsgException) ? ex.getMessage() : "购物车商品修改失败，请稍后重试";
        }
        return new BackMsg<>(errorCode, null, msg);
    }

    @Override
    public BackMsg<String> deleteCartItem(Integer skuId) {
        if (skuId == null || skuId <= 0) {
            return new BackMsg<>(DsmConcepts.ERROR, null, "skuId参数异常！");
        }
        User user = SessionToolUtils.getUser();
        int errorCode;
        String msg;
        try {
            boolean success;
            if (user != null) {
                ShoppingCartItem item = redisService.getHSetAsObject(login_cart_prov + user.getId(), skuId + "", ShoppingCartItem.class);
                if (item == null) {
                    throw new CustomErrorMsgException("请求信息数据过期，请刷新购物车重试！");
                }
                success = cartDao.deleteCartItem(item.getCartItemId()) > 0;
                if (success) {
                    redisService.del(login_cart_prov + user.getId());
                }

            } else {
                String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
                if (StringUtils.isBlank(unLoginUserKey)) {
                    throw new CustomErrorMsgException("请求信息数据过期，请刷新购物车重试！");
                }
                success = redisService.delHSet(noLogin_cart_prov + unLoginUserKey, skuId + "") > 0;
            }
            //校验删除情况
            if (!success) {
                errorCode = DsmConcepts.ERROR;
                msg = "删除失败";
            } else {
                errorCode = DsmConcepts.CORRECT;
                msg = "删除成功";
            }
        } catch (Exception ex) {
            errorCode = DsmConcepts.ERROR;
            msg = (ex instanceof CustomErrorMsgException) ? ex.getMessage() : "删除过程发生异常，请重试";
        }
        return new BackMsg<>(errorCode, null, msg);
    }

    @Override
    public BackMsg<String> cleanCartAll() {
        User user = SessionToolUtils.getUser();
        int errorCode;
        String msg;
        try {
            boolean success;
            if (user != null) {
                success = cartDao.cleanCartAll(user.getId()) > 0;
                if (success) {
                    redisService.del(login_cart_prov + user.getId());
                }
            } else {
                String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
                if (StringUtils.isBlank(unLoginUserKey)) {
                    throw new CustomErrorMsgException("请求信息数据过期，请刷新购物车重试！");
                }
                success = redisService.del(noLogin_cart_prov + unLoginUserKey);
            }
            //校验删除情况
            if (!success) {
                errorCode = DsmConcepts.ERROR;
                msg = "删除失败";
            } else {
                errorCode = DsmConcepts.CORRECT;
                msg = "删除成功";
            }
        } catch (Exception ex) {
            errorCode = DsmConcepts.ERROR;
            msg = (ex instanceof CustomErrorMsgException) ? ex.getMessage() : "删除过程发生异常，请重试";
        }
        return new BackMsg<>(errorCode, null, msg);
    }


    @Transactional
    @Override
    public void MergeNoLoginCacheToCart() {
        /**
         * 1.查询数据库中数据项，和缓存中对比，得到需要更新的列表项信息和需要新增的列表项目信息
         */

        /**
         * 2.对需要更新的列表想信息进行更新
         */

        /**
         * 3.对需要新增的列表项信息进行新增
         */

        /**
         * 4.若上述操作成功，则将信息设置到用户缓存中
         */

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
     * 这里只有用户信息不为空的时候才查询数据库更新，未登录用户直接更新信息
     * </p>
     *
     * @param user       用户信息，只有登录用户才有该信息
     * @param id         根据type的不同表示不同的含义，可以是skuId或者shopId
     * @param isSelected 选中状态，0表示不选中，1表示选中
     * @param type       类型，可以是sku shop 和 all
     */
    private void updateShoppingCartSelectedToCache(User user, Integer id, int isSelected, String type) {
        if (user != null) {
            if ("sku".equals(type)) {//选中单品
                ShoppingCartItem item = cartDao.getShoppingCartItemBySkuId(user.getId(), id);
                redisService.setHSet(login_cart_prov + user.getId(), item.getSkuId() + "", item);
            } else {
                List<ShoppingCartItem> list;
                if ("shop".equals(type)) {// 选中店铺所有单品
                    list = cartDao.getShoppingCartItemByShopId(user.getId(), id);
                } else {
                    list = cartDao.getShoppingCartInfoAll(user.getId());
                }

                if (list != null) {
                    //遍历将信息更新到redis缓存中
                    list.stream().forEach(cartItem -> {
                        cartItem.setIsSelected(isSelected);
                        redisService.setHSet(login_cart_prov + user.getId(), cartItem.getSkuId() + "", cartItem);
                    });
                }
            }
        } else {
            String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
            if ("sku".equals(type)) {    //选中单品操作
                ShoppingCartItem shoppingCartItem = redisService.getHSetAsObject(noLogin_cart_prov + unLoginUserKey, id + "", ShoppingCartItem.class);
                shoppingCartItem.setIsSelected(isSelected);
                redisService.setHSet(noLogin_cart_prov + unLoginUserKey, shoppingCartItem.getSkuId() + "", shoppingCartItem);
            } else {     //  店铺商品多选和全部商品多选操作
                //获取购物车所有项
                List<String> list = redisService.hvals(noLogin_cart_prov + unLoginUserKey);

                if (list != null) {
                    //流操作
                    Stream<ShoppingCartItem> stream = list.stream().map(s -> JSONObject.parseObject(s, ShoppingCartItem.class));
                    if ("shop".equals(type)) { //店铺全选操作
                        stream = stream.filter(item -> Objects.equals(item.getShopId(), id));
                    }
                    stream.forEach(afterItem -> {
                        afterItem.setIsSelected(isSelected);
                        redisService.setHSet(noLogin_cart_prov + unLoginUserKey, afterItem.getSkuId() + "", afterItem);
                    });
                }
            }
        }

    }


}
