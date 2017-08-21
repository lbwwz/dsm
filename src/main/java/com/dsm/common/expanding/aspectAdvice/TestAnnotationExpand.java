package com.dsm.common.expanding.aspectAdvice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/8/21
 *
 * @author : Lbwwz
 */
@Aspect
@Component
public class TestAnnotationExpand {



    @Around("com.dsm.common.expanding.aspectAdvice.DsmAspectPointCut.requestValidate()")
    public Object around(ProceedingJoinPoint pjp) {
        Object retVal = null;


//        JoinPointUtils.getParamByName(p)


//        pjp.et

        //编辑日志的输出格式
        try {
            retVal = pjp.proceed();
            //返回通知（发生异常不执行）

        }  catch (Throwable throwable) {
            //异常通知

        } finally{
            //信息待定
        }
        System.out.println("\n");
        return retVal;
    }
}
