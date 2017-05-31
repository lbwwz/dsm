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
    public static final int IMAGE_PRODUCT_TYPE = 1;         //图片类型-商品图
    public static final int IMAGE_PRODUCT_DETAIL_TYPE = 2;  //图片类型-商品详情描述图
    public static final int IMAGE_ADS_TYPE = 3;             //图片类型-广告图

    public static final int IS_NO_LOGIN = 0;      //登录状态——未登录
    public static final int IS_USER_LOGIN = 1;    //登录状态——普通用户登录
    public static final int IS_ADMIN_LOGIN = 2;   //登录状态——管理员用户登录

    public static final int SEARCH_SORT_DEFAULT=0;      //查找排序——默认
    public static final int SEARCH_SORT_HOT=1;          //查找排序——点击量
    public static final int SEARCH_SORT_CREDIT=2;          //查找排序——信用（商品好评率）
    public static final int SEARCH_SORT_PRICE_TO_LARGE=3;   //查找排序——价格从低到高
    public static final int SEARCH_SORT_PRICE_TO_SMALL=4;   //查找排序——价格从高到低
    /***** 业务相关编号 end *****/

    /*****  业务名称 start  *****/
    public static final String SESSION_USER = "user";   //普通用户
    public static final String SESSION_ADMIN = "admin"; //管理员用户
    public static final String LAST_ACCESS_URL ="lastAccessUrl"; //上页路径

    /***** 业务名称 end *****/



}
