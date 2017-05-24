package com.dsm.common.interceptor;

import com.dsm.common.DsmConcepts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lbw on 2016/9/2.
 * <p>
 * 拦截到 controller 层的所有请求
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {


//    private NamedThreadLocal<Long> curThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    /*
     * 跳转到登录页面上一个页面的的url
     */


    /**
     * 在处理到达 controller 前处理之前要拦截的
     * 处理记录请求的来源页面，将其保存在 session 属性 — lastAccessUrl  中
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查请求类型
        String requestType = request.getHeader("X-Requested-With");

        if (!"XMLHttpRequest".equals(requestType)) {    //非 ajax 请求
            if(request.getHeader("referer") == null || !request.getHeader("referer").contains("/showLogin")){
                String lastAccessUrl;
                if("GET".equalsIgnoreCase(request.getMethod())){
                    lastAccessUrl = request.getRequestURL() +
                            (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString());
                    //设置session属性-lastAccessUrl；用于记录用户上一个请求来源
                    request.getSession().setAttribute(DsmConcepts.LAST_ACCESS_URL, lastAccessUrl);
                }
            }
        }
        return true;
    }

}
