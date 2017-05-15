package com.dsm.common;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/15
 *
 * @author : Lbwwz
 * 业务参数含义默认配置
 */
public class DsmConcepts {

    /*****  {@link com.dsm.model.BackMsg} 错误号 start  *****/
    public static final int CORRECT = 0;    //操作成功
    public static final int ERROR = 1;      //操作失败
    public static final int NO_LOGIN = 100;      //用户未登录

    /* 文件上传 start */
    public static final int SIZE_WARRING = 101; //大小异常
    public static final int EXT_WARRING = 102;  //格式异常
    /*****  {@link com.dsm.model.BackMsg} 错误号 end  *****/

    /***** 业务相关编号 start*****/
    public static final int IMAGE_PRODUCT_TYPE = 1;  //图片类型-商品图
    public static final int IMAGE_PRODUCT_DETAIL_TYPE = 2;  //图片类型-商品详情描述图
    public static final int IMAGE_ADS_TYPE = 3;  //图片类型-广告图

    public static final int IS_NO_LOGIN = 0;  //图片类型-广告图
    public static final int IS_USER_LOGIN = 1;  //图片类型-广告图
    public static final int IS_ADMIN_LOGIN = 2;  //图片类型-广告图
    /***** 业务相关编号 end *****/

    /*****  业务名称 start  *****/
    public static final String SESSION_USER = "user"; //上传的文件
    public static final String SESSION_ADMIN = "admin"; //上传的文件
    public static final String SESSION_SHOP = "shop"; //上传的文件
    public static final String SESSION_UPLOAD_FILES = "uploadedFiles"; //上传的文件

    /***** 业务名称 end *****/



}
