package com.dsm.common.utils;

/**
 * Cookie工具类
 */

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param response {@link HttpServletResponse}
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) {
        addCookie(response, name, value, maxAge, false);
    }


    /**
     * 设置cookie
     *
     * @param response {@link HttpServletResponse}
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     * @param isHttpOnly
     *            设置httpOnly
     */
    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge, boolean isHttpOnly) {
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            cookie.setHttpOnly(isHttpOnly);
            response.addCookie(cookie);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 清空Cookie操作 clearCookie
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return boolean
     */
    public static boolean clearCookie(HttpServletRequest request,
                                      HttpServletResponse response, String name) {
        return clearCookie(request, response, name, null);
    }

    /**
     *
     * 清空Cookie操作 clearCookie
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param name
     * @param domain
     * @return
     */
    public static boolean clearCookie(HttpServletRequest request,
                                      HttpServletResponse response, String name, String domain) {
        boolean bool = false;
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) return false;
        try {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = new Cookie(name, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");// 根据你创建cookie的路径进行填写
                if (domain != null) {
                    cookie.setDomain(domain);
                }
                response.addCookie(cookie);
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
     * @param request {@link HttpServletRequest}
     * @param name cookie的键值
     * @return String
     */
    public static String getCookieByName(HttpServletRequest request,
                                         String name) {
        Cookie[] cookies = request.getCookies();
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

}
