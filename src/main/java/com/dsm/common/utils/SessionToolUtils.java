package com.dsm.common.utils;

import com.dsm.common.DsmConcepts;
import com.dsm.model.user.User;
import org.apache.shiro.SecurityUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

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


    public static User getUser(){
        User user =  (User)SecurityUtils.getSubject().getSession().getAttribute(DsmConcepts.SESSION_USER);
        return user== null?(User)SecurityUtils.getSubject().getSession().getAttribute(DsmConcepts.SESSION_ADMIN):user;

    }

    /**
     * 检查用户是否登录
     * @return 0：未登录；1：登录普通用户；2：登录管理员
     */
    public static int checkLogin(){
        int type = 0;
        if(SecurityUtils.getSubject().getSession().getAttribute(DsmConcepts.SESSION_USER)!=null){
            type = 1;
        }
        if(SecurityUtils.getSubject().getSession().getAttribute(DsmConcepts.SESSION_ADMIN)!=null){
            type = 2;
        }
        return type;
    }




}
