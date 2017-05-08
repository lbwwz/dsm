package com.dsm.common.utils;

import com.dsm.model.seller.Shop;
import com.dsm.model.user.User;
import org.apache.shiro.SecurityUtils;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/8
 *
 * @author : Lbwwz
 * <p/>
 * 操作session中的相关对象信息
 */
public class SessionToolUtils {

    public static Shop getShop(){
        return (Shop)SecurityUtils.getSubject().getSession().getAttribute("shop");
    }

    public static User getUser(){
        return (User)SecurityUtils.getSubject().getSession().getAttribute("user");
    }

}
