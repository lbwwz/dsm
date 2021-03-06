package com.dsm.common.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/23
 * <p/>
 * 校验重复提交的注解，建议标记在用于表单提交操作（CRUD操作）的controller方法上
 *
 * @author : Lbwwz
 * @see {@link com.dsm.common.expanding.aspectAdvice.RepeatSubmitCheckAnnotationAction}
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmitCheck {

    //判断重复提交时间间隔，单位：秒
    int timeInterval() default 3;

    //操作成功继续校验
    boolean successCheck() default true;
}
