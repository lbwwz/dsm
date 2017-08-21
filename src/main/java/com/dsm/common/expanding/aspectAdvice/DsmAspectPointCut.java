package com.dsm.common.expanding.aspectAdvice;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/14
 *
 * @author : Lbwwz
 */
public class DsmAspectPointCut {

    //service 记录日志切面
    @Pointcut("execution(* com.dsm.service.impls..*(..))")
    public void serviceOutputPoint(){}

    @Pointcut("execution(* com.dsm.controller..*(..))")
    public void requestValidate(){}
}
