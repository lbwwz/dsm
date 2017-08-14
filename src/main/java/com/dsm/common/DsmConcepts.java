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

    /*****  {@link com.dsm.model.BackMsg} 错误号，状态码 start  *****/
    public static final int CORRECT = 0;    //操作成功
    public static final int ERROR = 1;      //操作失败
    public static final int WARRING = 1;    //操作警告
    public static final int NO_LOGIN = 100;     //用户未登录
    public static final int NEED_REDIRECT = 9;  //需要跳转到其他页面

    /* 文件上传 start */
    public static final int SIZE_WARRING = 101; //大小异常
    public static final int EXT_WARRING = 102;  //格式异常

    /* 商品相关*/
    public static final int PRODUCT_NO_STOCK = 6;//库存不足
    /*****  {@link com.dsm.model.BackMsg} 错误号 end  *****/

    /***** 业务标志编号 start*****/
    public static final int IMAGE_PRODUCT_TYPE = 1;         //图片类型-商品图
    public static final int IMAGE_PRODUCT_DETAIL_TYPE = 2;  //图片类型-商品详情描述图
    public static final int IMAGE_ADS_TYPE = 3;             //图片类型-广告图

    public static final int IS_NO_LOGIN = 0;      //登录状态——未登录
    public static final int IS_USER_LOGIN = 1;    //登录状态——普通用户登录
    public static final int IS_ADMIN_LOGIN = 2;   //登录状态——管理员用户登录

    public static final int SEARCH_SORT_DEFAULT=0;      //查找排序——默认
    public static final int SEARCH_SORT_HOT=1;          //查找排序——点击量
    public static final int SEARCH_SORT_CREDIT=2;       //查找排序——信用（商品好评率）
    public static final int SEARCH_SORT_PRICE_TO_LARGE=3;   //查找排序——价格从低到高
    public static final int SEARCH_SORT_PRICE_TO_SMALL=4;   //查找排序——价格从高到低
    /***** 业务标志编号 end *****/

    /*****  业务名称 start  *****/
    public static final String SESSION_USER = "user";   //普通用户
    public static final String SESSION_ADMIN = "admin"; //管理员用户
    public static final String LAST_ACCESS_URL ="lastAccessUrl"; //上页路径
    public static final String DSM_SHOPPING_CART ="dsmShoppingCart"; //cookie中创建的购物车名

    public static final String DSM_USER_KEY = "user_key";//用来标志未登录用户的某些权益的唯一标志名

    public static final String MD5 = "MD5";
    public static final String SHA = "SHA";

    /***** 业务名称 end *****/


    /***** 业务中的常量 *****/

    public static final int LIST_PAGE_DEFAULT_NUM = 15;//默认每页查询的商品数量
    public static final int LIST_PAGE_SIZE = 100;      //默认最大分页页数


    //时间常量（单位：秒）
    public static final int MINUTE = 60;
    public static final int HOUR = MINUTE*60;
    public static final int DAY = HOUR*24;
    public static final int MONTH = DAY*30;
    public static final int YEAR = DAY*365;

    //时间常量（单位：毫秒）
    public static final int TIMESTAMP_MINUTE = MINUTE*1000;
    public static final int TIMESTAMP_HOUR = HOUR*1000;
    public static final int TIMESTAMP_DAY = DAY*1000;
    public static final int TIMESTAMP_MONTH = MONTH*1000;
    public static final int TIMESTAMP_YEAR = YEAR*1000;

    /***** 业务中的常量 *****/







}
