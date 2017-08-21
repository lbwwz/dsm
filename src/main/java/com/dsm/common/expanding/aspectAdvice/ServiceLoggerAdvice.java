package com.dsm.common.expanding.aspectAdvice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/14
 *
 * @author : Lbwwz
 *         <p/>
 *         记录service层的数据对象返回信息
 */
@Aspect
@Component
public class ServiceLoggerAdvice {

    Logger logger = LoggerFactory.getLogger(ServiceLoggerAdvice.class);

    @Around("com.dsm.common.expanding.aspectAdvice.DsmAspectPointCut.serviceOutputPoint()")
    public Object around(ProceedingJoinPoint pjp) {
        Object retVal = null;

        //编辑日志的输出格式
        try {
            retVal = pjp.proceed();
            //返回通知（发生异常不执行）
            logger.info("\n++++++[service传入的参数]:{}\n++++++[返回的结果]:{}",pjp.getSignature().toString()
                    + ":" + Arrays.toString(pjp.getArgs()), retVal);
        }  catch (Throwable throwable) {
            //异常通知
            logger.error("\n++++++[service发生异常]:{}", throwable.getMessage());
            throwable.printStackTrace();
        } finally{
            //信息待定
        }
        System.out.println("\n");
        return retVal;
    }


}
