package com.dsm.common.utils.funcInterface;

/**
 * Created by Lbwwz on 2016/8/9.
 */

/**
 * 函数式接口
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * 操作对象的接口方法接受一个参数T,返回相应的结果对线或值R
     * @param t 传入的参数
     * @return 返回参数 R——对应相应的泛型类型
     */
    R objHandler(T t);
}
