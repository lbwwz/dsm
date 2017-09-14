package com.dsm.service.impls;

import com.alibaba.fastjson.JSON;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.model.BackMsg;
import com.dsm.model.cart.ShoppingCartItem;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.IOrderService;
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


    @Override
    public BackMsg<String> checkOrder() {

        User user = SessionToolUtils.getUser();
        if (user == null) {
            return new BackMsg<>(DsmConcepts.NO_LOGIN, null, "请先登录");
        }

        List<String> ItemStrList = redisService.hvals(DsmConcepts.LOGIN_CART_PROV + user.getId());
        List<ShoppingCartItem> cartItemList = new ArrayList<>();

        ShoppingCartItem tempItem;
        for (String str : ItemStrList) {
            tempItem = JSON.parseObject(str, ShoppingCartItem.class);
            if (tempItem.getIsSelected() == 1) {
                cartItemList.add(tempItem);
            }
        }

        return null;
    }
}
