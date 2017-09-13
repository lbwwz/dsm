package com.dsm.common.utils;

/**
 * Cookie工具类
 */

import com.dsm.controller.common.RequestResponseContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;

public class CookieUtil {

    /**
     * 设置cookie（默认非httpOnly）
     *
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     */
    public static void addCookie(String name,String value, int maxAge) {
        addCookie(name, value, maxAge, false);
    }




    /**
     * 设置cookie
     *
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     * @param isHttpOnly
     *            设置httpOnly
     */
    public static void addCookie( String name,
                                 String value, int maxAge, boolean isHttpOnly) {
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            cookie.setHttpOnly(isHttpOnly);
            RequestResponseContext.getResponse().addCookie(cookie);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 清空Cookie操作 clearCookie
     *
     * @return boolean
     */
    public static boolean clearCookie(String name) {
        return clearCookie( name, null);
    }

    /**
     *
     * 清空Cookie操作 clearCookie
     *
     * @param name
     * @param domain
     * @return
     */
    public static boolean clearCookie(String name, String domain) {
        boolean bool = false;
        Cookie[] cookies = RequestResponseContext.getRequest().getCookies();
        if (null == cookies || cookies.length == 0) return false;
        try {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = new Cookie(name, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");// 根据你创建cookie的路径进行填写
                if (domain != null) {
                    cookie.setDomain(domain);
                }
                RequestResponseContext.getResponse().addCookie(cookie);
                bool = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bool;
    }

    /**
     * 获取指定cookies的值
     *
     * @param name cookie的键值
     * @return String
     */
    public static String getCookieByName(String name) {
        Cookie[] cookies = RequestResponseContext.getRequest().getCookies();
        if (null == cookies || cookies.length == 0) return null;
        String string = null;
        try {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (!StringUtils.isBlank(cookieName) && cookieName.equals(name)) {
                    string = cookie.getValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return string;
    }

    public static boolean checkCookieAbleToUsed(){
        return StringUtils.isNoneBlank(getCookieByName("JSESSIONID"));
    }

}
