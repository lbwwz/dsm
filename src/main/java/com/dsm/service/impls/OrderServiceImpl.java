package com.dsm.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.exception.CustomErrorMsgException;
import com.dsm.common.utils.EncryptUtils;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.dao.ICartDao;
import com.dsm.model.BackMsg;
import com.dsm.model.address.ShippingAddress;
import com.dsm.model.cart.ShoppingCartItemPO;
import com.dsm.model.formData.OrderCreateDto;
import com.dsm.model.order.OrderCheckCacheInfo;
import com.dsm.model.order.OrderCheckInfo;
import com.dsm.model.order.OrderPackage;
import com.dsm.model.order.OrderSkuItem;
import com.dsm.model.product.ProductSkuItem;
import com.dsm.model.user.User;
import com.dsm.service.base.IBusinessCacheService;
import com.dsm.service.interfaces.IOrderService;
import com.dsm.service.interfaces.IShippingAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 *         <p/>
 *         订单结算，操作等相关操作的业务类
 */

@Service("IOrderService")
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IRedisService redisService;

    @Autowired
    private IBusinessCacheService businessCacheService;

    @Autowired
    private IShippingAddressService shippingAddressService;

    @Resource
    private ICartDao cartDao;



    @Override
    public BackMsg<String> checkOrderInfo() {
        User user = SessionToolUtils.getUser();
        if (user == null) {
            return new BackMsg<>(DsmConcepts.NO_LOGIN, null, "请先登录");
        }


        List<OrderSkuItem> skuList;
        try {
            skuList = checkOrderSkuItemFromCart();
            if (skuList == null || skuList.size() == 0) {
                return new BackMsg<>(DsmConcepts.ERROR, null, "结算信息异常，请刷新重试");
            }
        } catch (Exception e) { //校验出现错误
            return new BackMsg<>(DsmConcepts.ERROR, null, (e instanceof CustomErrorMsgException) ? e.getMessage() : "商品校验异常");
        }
        String itemsString = makeItemsString(skuList);
        if (!skuList.get(0).getIsEnough()) {
            return new BackMsg<>(DsmConcepts.PRODUCT_NO_STOCK, itemsString, "存在商品库存不足");
        }
        setOrderSkuListToCache("orderCheck_" + user.getId(), EncryptUtils.encryptMD5(itemsString), skuList);
        return new BackMsg<>(DsmConcepts.CORRECT, itemsString, null);
    }

    /**
     * 根据购物车中选中的结算信息校验并获取sku信息列表，如果其中存在库存超出，返回超出库存的商品项
     * <p/>
     * 该方法用户购物车结算校验
     * 粗校验，只校验缓存时间段内商品库存
     *
     * @throws Exception sku信息失效
     */
    private List<OrderSkuItem> checkOrderSkuItemFromCart() throws Exception {
        List<ShoppingCartItemPO> cartList = getCartItemList();

        if (cartList == null || cartList.size() == 0) {
            return null;
        }

        List<OrderSkuItem> skuItemList = new ArrayList<>();
        List<OrderSkuItem> errorItemList = new ArrayList<>();
        ProductSkuItem skuItem;
        OrderSkuItem tempItem;
        for (ShoppingCartItemPO item : cartList) {
            if (item.getIsSelected() != 1) {
                continue;
            }
            //做库存校验
            tempItem = new OrderSkuItem();
            skuItem = businessCacheService.getProductSkuItemFromCache(false, item.getSkuId());
            if (skuItem == null) {
                throw new CustomErrorMsgException(item.getSkuId() + "对应的sku信息已失效！");
            }

            BeanUtils.copyProperties(skuItem, tempItem);
            //设置库存数量
            tempItem.setItemNum(item.getCartItemNum());
            if (tempItem.getQuantity() < item.getCartItemNum()) {//库存不足
                tempItem.setIsEnough(false);
                errorItemList.add(tempItem);
            } else {
                skuItemList.add(tempItem);
            }
        }

        //检查是否有库存异常
        if (errorItemList.size() != 0) {
            skuItemList = errorItemList;
        }


        return skuItemList;
    }


    /**
     * 根据items串 校验并获取结算sku信息列表，如果其中存在库存超出，返回超出库存的商品项
     * <p/>
     * 校验方式同{@link OrderServiceImpl#checkOrderSkuItemFromCart}
     *
     * @param items items串，格式:skuId1_数量1;skuId2_数量2
     * @throws Exception sku信息失效
     */
    private List<OrderSkuItem> checkOrderSkuItem(String items) throws Exception {
        if (items == null) {
            return null;
        }
        String[] itemStrArr = items.split(";");
        String[] tempArr;
        long skuId;
        int num;

        List<OrderSkuItem> skuItemList = new ArrayList<>();
        List<OrderSkuItem> errorItemList = new ArrayList<>();
        ProductSkuItem skuItem;
        OrderSkuItem tempItem;
        for (String itemStr : itemStrArr) {
            tempArr = itemStr.split("_");
            skuId = Integer.parseInt(tempArr[0]);
            num = Integer.parseInt(tempArr[1]);
            //做库存校验
            tempItem = new OrderSkuItem();
            skuItem = businessCacheService.getProductSkuItemFromCache(false, skuId);
            if (skuItem == null) {
                throw new CustomErrorMsgException(skuId + "对应的sku信息已失效！");
            }

            BeanUtils.copyProperties(skuItem, tempItem);
            //设置库存数量
            tempItem.setItemNum(num);
            if (tempItem.getQuantity() < num) {//库存不足
                tempItem.setIsEnough(false);
                errorItemList.add(tempItem);
            } else {
                skuItemList.add(tempItem);
            }
        }
        //检查是否有库存异常
        if (errorItemList.size() != 0) {
            skuItemList = errorItemList;
        }

        return skuItemList;
    }



    /**
     * 设置上次一次校验的orderSkuItem信息到缓存中
     *
     * @param key           缓存key
     * @param orderHashCode itemString组成的唯一编码
     * @param listInfo      orderSkuItem列表信息
     */
    private void setOrderSkuListToCache(String key, String orderHashCode, List<OrderSkuItem> listInfo) {
        long size = 5;
        if (!redisService.exists(key)) {
            redisService.setHSet(key, orderHashCode, JSONObject.toJSONString(new OrderCheckCacheInfo(listInfo)) + DsmConcepts.EXPIRE_TIME_SEPARATE + System.currentTimeMillis());
            redisService.expire(key, DsmConcepts.HOUR * 2);
        } else {
            redisService.setHSet(key, orderHashCode, JSONObject.toJSONString(new OrderCheckCacheInfo(listInfo)) + DsmConcepts.EXPIRE_TIME_SEPARATE + System.currentTimeMillis());
        }
        String tempKey = key + "_affiliate";
        redisService.removeListValue(tempKey, 1, orderHashCode);
        redisService.lpush(tempKey, orderHashCode);
        if (redisService.listLen(tempKey) > size) {
            String temp = redisService.rpop(tempKey);
            redisService.delHSet(key, temp);
        }
    }


    /**
     * 从当前用户的购物车中获取所有的购物车项
     * <p/>
     * 先从缓存获取，缓存失效则查询购物车表获取
     *
     * @return 购物车项信息列表
     */
    private List<ShoppingCartItemPO> getCartItemList() {
        User user = SessionToolUtils.getUser();
        List<String> cartItemPOList = redisService.hvals(DsmConcepts.LOGIN_CART_PROV + user.getId());
        List<ShoppingCartItemPO> tempList;
        if (cartItemPOList == null) {
            //如果不存在，从数据库中读取一次
            tempList = cartDao.getShoppingCartInfoAll(user.getId());
        } else {
            tempList = cartItemPOList.stream().map(item -> JSONObject.parseObject(item, ShoppingCartItemPO.class)).collect(Collectors.toList());
        }
        Collections.sort(tempList, (o1, o2) -> (int)(o1.getSkuId()-o2.getSkuId()));
        return tempList;
    }



    @Override
    public BackMsg<OrderCheckInfo> getOrderCheckInfo(String items, Long addressId) {

        /**
         * 先从缓存中获取结算项信息，如果获取信息为空，根据items串重新查询，
         */


        List<OrderSkuItem> skuList;

        try {
            skuList = getOrderSkuItemList(items);
        } catch (Exception e) { //校验出现错误
            return new BackMsg<>(DsmConcepts.ERROR, null, (e instanceof CustomErrorMsgException) ? e.getMessage() : "商品校验异常");
        }


        /**
         * 根据上述信息整理并封装为{@link OrderCheckInfo}对对象
         */
        List<ShippingAddress> orderAddresses = shippingAddressService.getConsigneeAddressList();
        //这里做信息封装
        OrderCheckInfo info = arrangeOrderCheckInfo(orderAddresses, skuList, addressId);
        if (info != null) {
            return new BackMsg<>(DsmConcepts.CORRECT, info, null);
        }
        //根据传入的itemId获取相关的信息，并返回给设置


        return null;
    }

    private List<OrderSkuItem> getOrderCheckInfo(String items) throws Exception {
        List<OrderSkuItem> skuList;User user = SessionToolUtils.getUser();
        skuList = getOrderSkuListFromCache("orderCheck_" + user.getId(), EncryptUtils.encryptMD5(items));
        if (skuList == null || skuList.size() == 0) {
            skuList = checkOrderSkuItem(items);
            if(skuList != null){
                String itemsString = makeItemsString(skuList);
                setOrderSkuListToCache("orderCheck_" + user.getId(), EncryptUtils.encryptMD5(itemsString), skuList);
            }
        }
        return skuList;
    }


    /**
     * 从之前结算时候创建的订单信息中获取结算项信息（只获取做信息展示，不需要进行库存校验）
     *
     * @param key 缓存KEY
     * @param orderHashCode hset中的key，是一串对应结算项的唯一编码
     */
    private OrderCheckCacheInfo getOrderCheckInfoFormCache(String key, String orderHashCode) {
        String cacheStr = redisService.getHSet(key, orderHashCode);

        if (StringUtils.isEmpty(cacheStr)) {
            return null;
        }
        String[] cacheInfo = cacheStr.split(DsmConcepts.EXPIRE_TIME_SEPARATE);
        if (System.currentTimeMillis() - Long.parseLong(cacheInfo[1]) > DsmConcepts.TIMESTAMP_MINUTE * 15) {
            return null;
        }
        return JSON.parseObject(cacheInfo[0], OrderCheckCacheInfo.class);
    }


    /**
     * 构造结算商品item字符串信息
     *
     * @param skuList 结算项信息
     * @return 结算信息串
     */
    private String makeItemsString(List<OrderSkuItem> skuList) {
        Collections.sort(skuList,(o1, o2) -> (int)(o1.getSkuId()-o2.getSkuId()));
        StringBuilder builder = new StringBuilder();

        for (OrderSkuItem item : skuList) {
            builder.append(item.getSkuId()).append("_").append(item.getItemNum()).append(";");
        }
        builder.substring(0, builder.length() - 1);
        return builder.toString();
    }

    /**
     * 整理结算页结算信息
     * <p>skuList结算项要判定是不是异常结算项目（库存不足），若是库存不足项，则需要进行先关的处理。。。</p>
     * （补充）使用缓存记录订单结算地址和费用信息
     *
     * @param orderAddresses 地址信息
     * @param skuList        结算项信息
     * @param addressId      结算选中地址
     * @return 结算信息封装对象
     */
    private OrderCheckInfo arrangeOrderCheckInfo(List<ShippingAddress> orderAddresses, List<OrderSkuItem> skuList, Long addressId) {

        if (skuList == null) {
            return null;
        }

        OrderCheckInfo orderCheckInfo = new OrderCheckInfo();

        Map<String, List<OrderSkuItem>> map = new HashMap<>();
        skuList.stream().forEach(item -> {
            List<OrderSkuItem> tempList = map.get(item.getShopId() + "");
            if (tempList == null) {
                tempList = new ArrayList<>();
                map.put(item.getShopId() + "", tempList);
            }
            tempList.add(item);
        });

        ShippingAddress selectedOrderAddress = null;
        if (!skuList.get(0).getIsEnough()) {    //库存校验不通过的操作
            orderCheckInfo.setIsEnough(false);
        } else {    //库存充足
            orderCheckInfo.setAddressList(orderAddresses);
            //设置结算页地址信息,并将地址信息返回
            selectedOrderAddress = doOrderSelectedAddress(orderAddresses, addressId);
        }

        /**
         * 价格统计。。。
         */
        BigDecimal packageTotalAmount = new BigDecimal(0);
        BigDecimal orderTotalAmount = new BigDecimal(0);
        OrderPackage tempPackage;
        //这里做订单操作的相关缓存信息
        for (Map.Entry<String, List<OrderSkuItem>> entry : map.entrySet()) {
            tempPackage = new OrderPackage(entry.getValue().get(0).getShopId(), entry.getValue().get(0).getShopName(), entry.getValue());
            orderCheckInfo.addOrderPackage(tempPackage);


            if(selectedOrderAddress != null){ //有物流地址时才进行订单核算
                //计算商品总价
                for (OrderSkuItem item : entry.getValue()) {
                    packageTotalAmount = packageTotalAmount.add(item.getSkuPrice().multiply(BigDecimal.valueOf(item.getItemNum())));
                }

                //运费计算



                //这里设置信息缓存(缓存时间15分钟)

                //优惠信息折算（不设置到缓存是为了防止优惠活动超时）

                //设置子单实际价格
                tempPackage.setShopTotalPrice(packageTotalAmount);

                //总价汇总
                orderTotalAmount = orderTotalAmount.add(packageTotalAmount);
            }
        }
        orderCheckInfo.setTotalPrice(orderTotalAmount);
        return orderCheckInfo;


    }

    /**
     * 设置结算页地址信息,并将该条地址信息返回
     *
     * @param orderAddresses 地址信息列表
     * @param addressId      要设置的地址信息的id
     * @return 设置选中的地址信息
     */
    private ShippingAddress doOrderSelectedAddress(List<ShippingAddress> orderAddresses, Long addressId) {
        if (orderAddresses == null || orderAddresses.size() == 0) {
            return null;
        }

        addressId = (addressId == null ? 0 : addressId);

        ShippingAddress temp = null;
        boolean selectFlag = true;
        for (ShippingAddress address : orderAddresses) {
            if (addressId == 0 && address.getIsDefault() == 1) {
                temp = address;
                address.setCheckOrderSelected(true);
                break;


            } else {
                if (address.getIsDefault() == 1) {
                    temp = address;
                }
                if (addressId == address.getAddressId()) {
                    temp = address;
                    address.setCheckOrderSelected(true);
                    selectFlag = false;
                    break;
                }
            }
        }
        if (selectFlag) {
            //其他情况默认选中地址第一个
            temp = temp == null ? orderAddresses.get(0) : temp;
            temp.setCheckOrderSelected(true);
        }
        return temp;
    }


    public BackMsg<String[]> createOrder(OrderCreateDto dto){

        User user = SessionToolUtils.getUser();
        List<OrderSkuItem> orderSkuItems = getOrderSkuListFromCache("orderCheck_" + user.getId(), EncryptUtils.encryptMD5(dto.getItemString()));

        return null;

    }


}
