package com.dsm.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.exception.CustomErrorMsgException;
import com.dsm.common.utils.CookieUtil;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.dao.ICartDao;
import com.dsm.model.BackMsg;
import com.dsm.model.cart.CartPackage;
import com.dsm.model.cart.ShoppingCart;
import com.dsm.model.cart.ShoppingCartItem;
import com.dsm.model.cart.ShoppingCartItemPO;
import com.dsm.model.product.ProductSkuItem;
import com.dsm.model.user.User;
import com.dsm.service.base.IBusinessCacheService;
import com.dsm.service.interfaces.ICartService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/15
 *
 * @author : Lbwwz
 * 购物车操作的相关业务逻辑
 *
 * <p>
 *     注意点：涉及到数量更新操作的位置都需要检验库存（强制查询数据库）：
 * </p>
 */
@Service("ICartService")
public class CartServiceImpl implements ICartService {

    @Resource
    private ICartDao cartDao;


    @Resource
    private IRedisService redisService;

    //基本操作的缓存服务
    @Autowired
    private IBusinessCacheService businessCacheService;


    public static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public ShoppingCart getMyShoppingCart() {
        List<ShoppingCartItem> cartInfoList = getShoppingCartItems();

        ShoppingCart cartInfo = null;
        if (cartInfoList != null) {
            //这里进行购物车信息的封装和计算处理
            cartInfo = arrangeCartInfo(cartInfoList);
        }
        return cartInfo;

    }

    /**
     * 获取 当前访客的 careItemList
     *
     */
    private List<ShoppingCartItem> getShoppingCartItems() {
        //校验是否登录
        User user = SessionToolUtils.getUser();
//        String

        List<ShoppingCartItemPO> cartItemPOList;
        if (user != null) {
            cartItemPOList = getCartItemListFromCache(DsmConcepts.LOGIN_CART_PROV + user.getId());
            if (cartItemPOList == null) {
                //如果不存在，从数据库中读取一次
                cartItemPOList = cartDao.getShoppingCartInfoAll(user.getId());
                setCartItemListToCache(DsmConcepts.LOGIN_CART_PROV + user.getId(), cartItemPOList);
            }
        } else {
            //未登录用户使用用户唯一标志符查找缓存中的购物车信息
            String userKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);

            if (StringUtils.isBlank(userKey)) {
                //标志key不存在，设置唯一标志key
                CookieUtil.addCookie(DsmConcepts.DSM_USER_KEY, UUID.randomUUID().toString(), DsmConcepts.DAY, true);
            }
            cartItemPOList = getCartItemListFromCache(DsmConcepts.NO_LOGIN_CART_PROV + userKey);

        }
        return getCartInfoListFromDataBase(cartItemPOList);
    }


    /**
     * 从缓存中获取购物车数据信息
     *
     * @param cartKey 缓存key
     * @return 购物车子项列表信息
     */
    private List<ShoppingCartItemPO> getCartItemListFromCache(String cartKey) {
        if (redisService.exists(cartKey)) {
            List<String> List = redisService.hvals(cartKey);
            if(SessionToolUtils.checkLogin() == 0){
                redisService.expire(cartKey, DsmConcepts.DAY);
            }
            List<ShoppingCartItemPO> cartItemPOList = new ArrayList<>();
            for (String str : List) {
                cartItemPOList.add(JSON.parseObject(str,ShoppingCartItemPO.class));
            }
            return cartItemPOList;
        }
        return null;
    }

    /**
     * 设置购物车信息到缓存中
     *
     * @param cartKey      redis中购物车项的缓存的key
     * @param cartItemList 购物车条目信息列表
     */
    private boolean setCartItemListToCache(String cartKey, List<ShoppingCartItemPO> cartItemList) {
        Map<String, ShoppingCartItemPO> m = new HashMap<>();
        for (ShoppingCartItemPO item : cartItemList) {
            m.put(item.getSkuId() + "", item);
        }
        if (redisService.setHmset(cartKey, m)) {
            redisService.expire(cartKey, getExpireTime());
            return true;
        }
        return false;
    }



    private int getExpireTime() {
        int expireTime = DsmConcepts.DAY;
        //登录的用户缓存时间为30分钟
        if(SessionToolUtils.checkLogin() !=0){
            expireTime = DsmConcepts.MINUTE*30;
        }
        return expireTime;
    }


    /**
     * 整理购物信息
     *
     * @param cartInfoList 待处理的购物车元数据列表
     * @return 购物车数据对象
     */
    private ShoppingCart arrangeCartInfo(List<ShoppingCartItem> cartInfoList) {

        Map<String, List<ShoppingCartItem>> map = new HashMap<>();

        //日期新的排在前面
        Collections.sort(cartInfoList, (o1, o2) -> o1.getCreateTime().compareTo(o2.getCreateTime()));
        List<ShoppingCartItem> tempList;
        for (ShoppingCartItem item : cartInfoList) {
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

        ProductSkuItem thisSkuItem = businessCacheService.getProductSkuItemFromCache(skuId,true);
        if (thisSkuItem == null) {
            return new BackMsg<>(DsmConcepts.WARRING, null, "操作的该商品项不可用！");
        }
        int errorCode = DsmConcepts.CORRECT;
        String msg = null;
        try {
            User user = SessionToolUtils.getUser();
            String redisCartKey = getCartCacheKey(user);
            ShoppingCartItemPO cartItemPO = redisService.getHSetAsObject(redisCartKey, skuId + "", ShoppingCartItemPO.class);
//            if (cartItem == null && changeCount < 0) {
//                return new BackMsg<>(DsmConcepts.WARRING, null, "无效的操作!");
//            }
            if (changeCount != null) {
                changedCount = changeCount + (cartItemPO == null ? 0 : cartItemPO.getCartItemNum());
            }
            if (thisSkuItem.getQuantity() < changedCount  ) {
                changedCount = thisSkuItem.getQuantity();
                if(changeCount >0){
                    errorCode = DsmConcepts.PRODUCT_NO_STOCK;
                    msg = "最大库存不足！";
                }
            }
                /*
                 *  2.查询缓存中该用户对应的购物车信息，若存在则更新，若不存在，则新增
                 */
            if (cartItemPO != null && changedCount <= 0) {
                //？可以更新为删除操作
                throw new CustomErrorMsgException("修改操作不能将商品数量变为0！");
            }
            if (user != null) {
                long opFlag;
                if (cartItemPO == null) {
                    cartItemPO = new ShoppingCartItemPO(user.getId(), thisSkuItem.getShopId(),
                            skuId, changedCount, 1);

                    opFlag = cartDao.addCartItem(cartItemPO);
                } else {
                    cartItemPO.setCartItemNum(changedCount);
                    cartItemPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    opFlag = cartDao.updateCartItem(cartItemPO);
                }
                if (opFlag <= 0) {
                    throw new Exception();
                }

                cartItemPO = cartDao.getShoppingCartItem(cartItemPO.getCartItemId());
            } else {
                if (cartItemPO == null) {
                    cartItemPO = new ShoppingCartItemPO(null,thisSkuItem.getShopId(),thisSkuItem.getSkuId(),changedCount,1);
                    cartItemPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    cartItemPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                }else{
                    cartItemPO.setCartItemNum(changedCount);
                    cartItemPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                }
            }
            //3.将最新的信息，更新到redis缓存
            redisService.setHSet(redisCartKey, skuId + "", cartItemPO);
            redisService.expire(redisCartKey, getExpireTime());
            msg = (errorCode == DsmConcepts.CORRECT) ? "购物车商品修改成功" : msg;
        } catch (Exception ex) {
            errorCode = DsmConcepts.ERROR;
            msg = (ex instanceof CustomErrorMsgException) ? ex.getMessage() : "购物车商品修改失败，请稍后重试！";
        }
        return new BackMsg<>(errorCode, null, msg);
    }

    private String getCartCacheKey(User user) {
        String redisCartKey;
        if (user != null) {     //登录的用户
            redisCartKey = DsmConcepts.LOGIN_CART_PROV + user.getId();
        } else { //未登录用户
            String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
            if (StringUtils.isBlank(unLoginUserKey)) {
                //user_key 为空，则生成并设置到cookie中
                unLoginUserKey = UUID.randomUUID().toString();
                CookieUtil.addCookie(DsmConcepts.DSM_USER_KEY, unLoginUserKey, DsmConcepts.MONTH, true);
            }
            redisCartKey = DsmConcepts.NO_LOGIN_CART_PROV + unLoginUserKey;
        }
        return redisCartKey;
    }


    @Override
    public BackMsg<String> changeItemsSelected(Integer id, int isSelected, String type) {

        int errorCode;
        String msg;
        try {
            updateShoppingCartSelectedToCache(id, isSelected, type);
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
                ShoppingCartItem item = redisService.getHSetAsObject(DsmConcepts.LOGIN_CART_PROV + user.getId(), skuId + "", ShoppingCartItem.class);
                if (item == null) {
                    throw new CustomErrorMsgException("请求信息数据过期，请刷新购物车重试！");
                }
                success = cartDao.deleteCartItem(item.getCartItemId()) > 0;
                if (success) {
                    redisService.del(DsmConcepts.LOGIN_CART_PROV + user.getId());
                }

            } else {
                String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
                if (StringUtils.isBlank(unLoginUserKey)) {
                    throw new CustomErrorMsgException("请求信息数据过期，请刷新购物车重试！");
                }
                success = redisService.delHSet(DsmConcepts.NO_LOGIN_CART_PROV + unLoginUserKey, skuId + "") > 0;
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
                    redisService.del(DsmConcepts.LOGIN_CART_PROV + user.getId());
                }
            } else {
                String unLoginUserKey = CookieUtil.getCookieByName(DsmConcepts.DSM_USER_KEY);
                if (StringUtils.isBlank(unLoginUserKey)) {
                    throw new CustomErrorMsgException("请求信息数据过期，请刷新购物车重试！");
                }
                success = redisService.del(DsmConcepts.NO_LOGIN_CART_PROV + unLoginUserKey);
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
         * 检查是否需要执行合并操作
         */

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
     * 更新缓存中的购物车选中信息
     *
     * @param id         根据type的不同表示不同的含义，可以是skuId或者shopId
     * @param isSelected 选中状态，0表示不选中，1表示选中
     * @param type       类型，可以是sku shop 和 all
     */
    private void updateShoppingCartSelectedToCache(Integer id, int isSelected, String type) {
        String cacheKey = getCartCacheKey(SessionToolUtils.getUser());
        if ("sku".equals(type)) {    //选中单品操作
            ShoppingCartItemPO shoppingCartItemPO = redisService.getHSetAsObject(cacheKey, id + "", ShoppingCartItemPO.class);
            shoppingCartItemPO.setIsSelected(isSelected);
            redisService.setHSet(cacheKey, shoppingCartItemPO.getSkuId() + "", shoppingCartItemPO);
        } else {     //  店铺商品多选和全部商品多选操作
            //获取购物车所有项
            List<String> list = redisService.hvals(cacheKey);

            if (list != null) {
                //流操作
                Stream<ShoppingCartItemPO> stream = list.stream().map(s -> JSONObject.parseObject(s, ShoppingCartItemPO.class));
                if ("shop".equals(type)) { //店铺全选操作
                    stream = stream.filter(item -> Objects.equals(item.getShopId(), id));
                }
                stream.forEach(afterItem -> {
                    afterItem.setIsSelected(isSelected);
                    redisService.setHSet(cacheKey, afterItem.getSkuId() + "", afterItem);
                });
            }
        }
        redisService.expire(cacheKey,getExpireTime());
    }



    /**
     * 由ShoppingCartItemPO对象获取完整的 ShoppingCartItem 对象信息;
     * @param cartItemPo 购物车po数据对象
     */
    private ShoppingCartItem getCartItemFromDataBase(ShoppingCartItemPO cartItemPo){
        if(cartItemPo == null || cartItemPo.getSkuId() == null){
            return null;
        }
        try{
            ShoppingCartItem item = new ShoppingCartItem();
            BeanUtils.copyProperties(businessCacheService.getProductSkuItemFromCache(cartItemPo.getSkuId(),false),item);
            //库存超出时不能选中
            if(cartItemPo.getCartItemNum()>item.getQuantity()){
                cartItemPo.setIsSelected(0);
                redisService.setHSet(getCartCacheKey(SessionToolUtils.getUser()),cartItemPo.getSkuId()+"",cartItemPo);
                //设置库存充足属性为否
                item.setIsEnough(false);
            }
            BeanUtils.copyProperties(cartItemPo,item);

            return item;
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 由ShoppingCartItemPO对象列表获取完整的 ShoppingCartItem 对象列表信息;
     * @param cartItemPoList 购物车po数据对象列表
     */
    private List<ShoppingCartItem> getCartInfoListFromDataBase(List<ShoppingCartItemPO> cartItemPoList){
        if(cartItemPoList == null)return null;

        List<ShoppingCartItem> list = new ArrayList<>();
        ShoppingCartItem cartItem;
        for(ShoppingCartItemPO cartItemPO : cartItemPoList){
            //查询sku的信息
            if((cartItem = getCartItemFromDataBase(cartItemPO))!=null){
                list.add(cartItem);
            }
        }
        return list;
    }


}
