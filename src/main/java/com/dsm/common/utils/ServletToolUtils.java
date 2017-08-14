package com.dsm.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

/**
 * 关于servlet容器中（controller层中）一些操作的工具类
 *
 * @author lbwwz
 */

public class ServletToolUtils {

    /**
     * 校验页面表单是否重复提交
     *
     * @param request {@link HttpServletRequest}
     * @param formTokenName:    随着form表单一同提交的 token 标志名称
     * @param sessionTokenName: session中设置的 token 标志名称
     * @return 校验是否通过，true表示校验成功，false表示校验失败
     */
    public static boolean checkRepeatSubmit(HttpServletRequest request, String formTokenName,
                                            String sessionTokenName){
        return checkRepeatSubmit(request.getSession(),request.getParameter(formTokenName),sessionTokenName);
    }

    /**
     * 校验页面表单是否重复提交
     *
     * @param session {@link HttpSession}
     * @param formToken:    随着form表单一同提交的token
     * @param sessionTokenName: session中设置的token标志名称
     * @return 校验是否通过，true表示校验成功，false表示校验失败
     */
    public static boolean checkRepeatSubmit(HttpSession session, String formToken,
                                            String sessionTokenName){

        String sessionToken = (String)session.getAttribute(sessionTokenName);
        //session中的校验符为空，表示未设置校验，直接返回true
        if(sessionToken == null){
            return false;
        }else if(sessionToken.equals(formToken)) {//设置校验而且校验通过
            /**
             * 设置临时token属性，存放当次表单请求的token，当表单提交发生系统异常时用于设置token回滚
             */
            session.setAttribute("dsmTempTokenParam", sessionTokenName+":"+sessionToken);
            // 移除标志属性
            session.removeAttribute(sessionTokenName);
            // 检测非重复提交返回true
            return true;
        }
        //校验失败表示重复提交返回false
        return false;
    }

    /**
     * 表单提交查重的 token 回滚
     */
    public static void rollBackToken() {
        Session session = SecurityUtils.getSubject().getSession();
        String tokenParam = ((String) session.getAttribute("dsmTempTokenParam"));
        if (StringUtils.isNoneEmpty(tokenParam)) {
            String[] params = tokenParam.split(":");
            session.setAttribute(params[0],params[1]);
        }
    }


    /**
     * 使用反射的方式调用servlet中对应method属性对应名称的方法
     *
     * @param obj      方法所在的servlet对象
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @SuppressWarnings("unused")
    public static void doMethod(Object obj, HttpServletRequest request, HttpServletResponse response)
            throws RuntimeException {
        String methodName = request.getParameter("method");
        System.out.println(methodName);
        // 根据 methodName 使用反射调用对应的方法
        try {
            ReflectionUtils.invokeMethod(obj, methodName,
                    new Class<?>[]{HttpServletRequest.class, HttpServletResponse.class}, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用反射获取request对象中的Parameter信息，并将其封装到对应的formBean对象中
     * <p>
     * 此方法适用情况：formBean 对象的属性名和  ParameterNames 一一对应
     *
     * @param request   HttpServletRequest
     * @param beanClass 需要反射创建的bean对象的Class类对象
     * @return 相应的 <T> formBean 对象
     */
    @SuppressWarnings("unused")
    public static <T> T getRequestBean(HttpServletRequest request,
                                       Class<T> beanClass) {
        try {
            //使用反射创建对应的  bean 对象
            T bean = beanClass.newInstance();

            //获取 ParameterNames，并循环通过反射为新创建的bean对象的属性赋值
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 爬虫检验
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @return 校验的结论
     */
    @SuppressWarnings("unused")
    public static boolean spiderCheck(HttpServletRequest request, HttpServletResponse response) {
        Boolean comeFromSpider = Boolean.FALSE;
        String userAgent = request.getHeader("User-Agent");

        if (StringUtils.contains(userAgent, "Baiduspider") || StringUtils.contains(userAgent, "360Spider") || StringUtils.contains(userAgent, "HaosouSpider")
                || StringUtils.contains(userAgent, "Yisouspider") || StringUtils.contains(userAgent, "Googlebot")
                || StringUtils.contains(userAgent, "Bingbot") || StringUtils.contains(userAgent, "MSNbot")) {
            comeFromSpider = Boolean.TRUE;
            response.addHeader("Vary", "User-Agent");
        }
        return comeFromSpider;
    }

}
