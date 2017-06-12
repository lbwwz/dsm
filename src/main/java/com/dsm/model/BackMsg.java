package com.dsm.model;

/**
 * 操作后的信息返回对象
 * Created by Lbwwz on 2016/8/8.
 */
public class BackMsg<T> {

    public static final int CORRECT = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;

    /**
     * 异常指示符，0表示没有异常，1表示错误，大于1表示警告
     */
    private int error;

    /**
     * 执行操作后返回的数据
     * -有时候也用来作为一类异常指示符下的错误区分信息
     */
    private T data;

    /**
     * 对应异常信息显示的message
     */
    private String message;

    public BackMsg() {
    }

    public BackMsg(int error,T data, String message) {
        this.error = error;
        this.data = data;
        this.message = message;
    }

    public void set(int error,T data, String message) {
        this.error = error;
        this.data = data;
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
