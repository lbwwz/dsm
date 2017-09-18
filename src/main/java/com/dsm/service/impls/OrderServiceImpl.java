package com.dsm.service.impls;

import com.alibaba.fastjson.JSON;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.model.BackMsg;
import com.dsm.model.cart.ShoppingCartItemPO;
import com.dsm.model.user.User;
import com.dsm.service.base.IBusinessCacheService;
import com.dsm.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 */

@Service("IOrderService")
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IRedisService redisService;

    @Autowired
    private IBusinessCacheService businessCacheService;


    @Override
    public BackMsg<String> checkCartItem() {

        User user = SessionToolUtils.getUser();
        if (user == null) {
            return new BackMsg<>(DsmConcepts.NO_LOGIN, null, "请先登录");
        }


        /**
         * 获取选中的购物车商品项
         */
        List<String> ItemStrList = redisService.hvals(DsmConcepts.LOGIN_CART_PROV + user.getId());
        List<ShoppingCartItemPO> cartItemList = new ArrayList<>();
        ShoppingCartItemPO tempItem;
        for (String str : ItemStrList) {
            tempItem = JSON.parseObject(str, ShoppingCartItemPO.class);
            if (tempItem.getIsSelected() == 1) {
                businessCacheService.getProductSkuItemFromCache(tempItem.getSkuId(),false);
                cartItemList.add(tempItem);
            }
        }

        return new BackMsg(DsmConcepts.CORRECT,cartItemList,"获取选中商品信息成功");
    }
}
