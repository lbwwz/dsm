package com.dsm.common.expanding.aspectAdvice.utils;

import org.aspectj.lang.JoinPoint;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/8/21
 *
 * @author : Lbwwz
 * <p>aop 链接点操作的一些操作</p>
 */
public class JoinPointUtils {


    /**
     * 获取切点方法的某个参数名对应的参数
     * @param joinPoint 连接点
     * @param paramName 参数名称
     * @param <T> 返回值类型
     * @return 返回参数
     */
    public static <T> T getParamByName(JoinPoint joinPoint,String paramName){
        Class clazz = joinPoint.getSourceLocation().getWithinType();

        String method = joinPoint.getSignature().getName();



        return (T)null;

    }

}
