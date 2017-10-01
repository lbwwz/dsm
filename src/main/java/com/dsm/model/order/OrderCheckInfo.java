package com.dsm.model.order;

import com.dsm.model.address.ShippingAddress;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 *         订单结算清单页面的相关信息(对应confirm_order页面)
 */
public class OrderCheckInfo{

    //收获地址信息地址信息
    private List<ShippingAddress> addressList;

    private List<OrderPackage> orderPackages;

    private boolean isEnough = true;

    //商品总额
    private BigDecimal totalPrice;

    public List<ShippingAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<ShippingAddress> addressList) {
        this.addressList = addressList;
    }

    public List<OrderPackage> getOrderPackages() {
        return orderPackages;
    }

    public void setOrderPackages(List<OrderPackage> orderPackages) {
        this.orderPackages = orderPackages;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getIsEnough() {
        return isEnough;
    }

    public void setIsEnough(boolean isEnough) {
        this.isEnough = isEnough;
    }

    public void addOrderPackage(OrderPackage orderPackage) {
        if(this.orderPackages == null){
            this.orderPackages = new ArrayList<>();
        }
        this.orderPackages.add(orderPackage);
    }

    @Override
    public String toString() {
        return "OrderCheckInfo{" +
                "addressList=" + addressList +
                ", orderPackages=" + orderPackages +
                ", isEnough=" + isEnough +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
//    /**
//     * 根据选中的结算信息校验并获取sku信息列表，如果其中存在库存超出，返回超出库存的商品项
//     */
//    private List<OrderSkuItem> checkOrderSkuItem() throws Exception{
//
//
//        List<ShoppingCartItemPO> cartList = getCartItemList();
//
//        if(cartList == null || cartList.size() == 0){
//            return null;
//        }
//
//        List<OrderSkuItem> skuItemList = new ArrayList<>();
//        List<OrderSkuItem> errorItemList = new ArrayList<>();
//        ProductSkuItem skuItem;
//        OrderSkuItem tempItem;
//        for(ShoppingCartItemPO item  : cartList){
//            if(item.getIsSelected() != 1){
//                continue;
//            }
//            //做库存校验
//            tempItem = new OrderSkuItem();
//
//            skuItem = businessCacheService.getProductSkuItemFromCache(false, item.getSkuId());
//            if(skuItem == null){
//                throw new CustomErrorMsgException(item+"对应的sku信息已失效！");
//            }
//
//            BeanUtils.copyProperties(skuItem,tempItem);
//            //设置库存数量
//            tempItem.setItemNum(item.getCartItemNum());
//            if(tempItem.getQuantity()<= item.getCartItemNum()){//库存不足
//                tempItem.setIsEnough(false);
//                errorItemList.add(tempItem);
//            }else{
//                skuItemList.add(tempItem);
//            }
//        }
//
//        //检查是否有库存异常
//        if(errorItemList.size() != 0){
//            skuItemList = errorItemList;
//        }
//
//
//        return skuItemList;
//    }
//private List<ShoppingCartItemPO> getCartItemList() {
//    User user = SessionToolUtils.getUser();
//    List<String> cartItemPOList = redisService.hvals(DsmConcepts.LOGIN_CART_PROV + user.getId());
//    List<ShoppingCartItemPO> tempList;
//    if (cartItemPOList == null) {
//        //如果不存在，从数据库中读取一次
//        tempList =  cartDao.getShoppingCartInfoAll(user.getId());
//    }else{
//        tempList = cartItemPOList.stream().map(item-> JSONObject.parseObject(item,ShoppingCartItemPO.class)).collect(Collectors.toList());
//    }
//    Collections.sort(tempList,(o1, o2)->o1.getSkuId()-o2.getSkuId());
//    return tempList
//}