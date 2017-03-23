package com.dsm.common.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Lbw on 2016/9/2.
 * <p>
 * 拦截到 controller 层的所有请求
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {


//    private NamedThreadLocal<Long> curThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");


    /**
     * 在处理到达 controller 前处理之前要拦截的
     * 处理记录请求的来源页面，将其保存在 session 属性 — lastAccessUrl  中
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查请求类型
        String requestType = request.getHeader("X-Requested-With");

        if (!"XMLHttpRequest".equals(requestType)) {    //过滤 ajax请求
            if(request.getHeader("referer") == null || !request.getHeader("referer").contains("/showLogin")){

                String lastAccessUrl;
                if("GET".equalsIgnoreCase(request.getMethod())){
                    lastAccessUrl = request.getRequestURL() +
                            (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString());
                    //设置session属性-lastAccessUrl；用于记录用户上一个请求来源

                }else{
                    lastAccessUrl = request.getRequestURL().toString();
                    //保存请求携带的post信息数据
                    Map<String, String[]> map = request.getParameterMap();
                    if(map.size() != 0){
                        request.getSession().setAttribute("lastPostData", map);
                    }
                }
                request.getSession().setAttribute("lastAccessUrl", lastAccessUrl);
            }else{ //登录页面的跳出（设置之前跳入请求post提交的数据）//待定？？？
                Map<String, String[]> m = (Map<String, String[]>) request.getSession().getAttribute("lastPostData");
                if(m != null){
                    for(Map.Entry<String,String[]> entry : m.entrySet()){
                        request.setAttribute(entry.getKey(),m.get(entry.getKey()));
                    }
                    //post数据赋值完毕，从session中移除相应的数据
                    request.getSession().removeAttribute("lastPostData");
                }
            }
        }

        return true;
    }

    /**
     * 控制器执行完，生成视图的处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 一般用于释放一些被占用的公共资源
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
