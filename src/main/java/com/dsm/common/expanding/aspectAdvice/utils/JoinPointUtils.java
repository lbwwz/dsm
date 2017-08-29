package com.dsm.common.expanding.aspectAdvice.utils;

import com.dsm.common.annotation.TestAnnotation;
import org.aspectj.lang.JoinPoint;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

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
     * @param index 参数索引位置
     * @param <T> 返回值类型
     * @return 返回参数
     */
    public static <T> T getParamByIndex(JoinPoint joinPoint,int index){
        int argsLength = joinPoint.getArgs().length;

        if(index >argsLength-1){
            index=argsLength-1;
        }else if(index<0){
            index = 0;
        }

        return (T)joinPoint.getArgs()[index];

    }


    /**
     * 获取aop切点所处理的方法
     * @param joinPoint 连接点
     * @return 所在方法
     */
    public static Method getMethod(JoinPoint joinPoint){

        Method[] methods = joinPoint.getSignature().getDeclaringType().getDeclaredMethods();
        for(Method method : methods){
            if(method.toString().equals(joinPoint.getSignature().toLongString()))
                return method;
        }

        return null;
    }

}
