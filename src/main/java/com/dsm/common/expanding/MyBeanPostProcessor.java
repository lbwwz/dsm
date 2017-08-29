package com.dsm.common.expanding;

import com.dsm.common.annotation.TestAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/8/21
 *
 * @author : Lbwwz
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
                TestAnnotation annotation = AnnotationUtils.findAnnotation(method, TestAnnotation.class);
                if(annotation != null){
                    //对标有某个注解的方法进行方法增强
                    System.out.println(bean);







                }
            }
        }
        return bean;
    }


}
