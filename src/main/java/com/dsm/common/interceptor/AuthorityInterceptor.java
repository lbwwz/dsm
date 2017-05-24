package com.dsm.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.model.BackMsg;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lbw on 2016/9/2.
 *
 * 登录权限校验的拦截器(没有登录会引导用户走登录流程操作)
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    /**
     * 在处理到达 controller 前处理之前要拦截的
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //校验用户是否已经登录
        boolean isLogin = request.getSession().getAttribute("user")!=null;

        //用户具有页面访问权限
        if(isLogin){
            return true;
        }else{
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {    //ajax请求
                response.setContentType("text/javascript; charset=utf-8");
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(JSONObject.toJSONString(new BackMsg(DsmConcepts.NO_LOGIN,"","登录信息失效，请重新登录！")));
            }else{ //非ajax请求
                //用户不具有权限,让用户跳转到登录页
                response.sendRedirect("/showLogin");
            }
            return false;
        }

    }

//    /**
//     * 控制器执行完，生成视图的处理
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler,ModelAndView modelAndView) throws Exception {
//
//    }

//    /**
//     * 一般用于释放一些被占用的公共资源
//     *
//     * @param httpServletRequest
//     * @param httpServletResponse
//     * @param o
//     * @param e
//     * @throws Exception
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                                Object o, Exception e)throws Exception {
//
//    }

}
