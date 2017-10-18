package com.dsm.common.utils;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/7/15
 *
 * @author : Lbwwz
 * <p>
 *     关于业务操作的一些算法封装
 * </p>
 */
public class BusinessUtils {


    public static long TIMESTAMP_COVER = 0xffffffffffL;


    /**
     *
     * 订单ID生成器
     * 订单类型+店铺id（后三位）+时间戳信息+用户ID（后四位）
     * @param shopId 商品所在的店铺id
     * @param orderType
     */
    public static String orderIdGenerate(long shopId, int orderType){

        long now = System.currentTimeMillis();
        long timestamp = now&TIMESTAMP_COVER;
        String shopStamp = getLastIdStamp(shopId,3);
        return orderType+shopStamp+timestamp+
                getLastIdStamp((long) SessionToolUtils.getUser().getId(),4);


    }

    private static String getLastIdStamp(long id,int length){
        String zeroStr = "";
        for(int i = length ;i>0; i--){
            zeroStr+="0";
        }
        String temp = zeroStr+id;
        return temp.substring(temp.length()-length);
    }


    public static String orderIdGenerate(long shopId){
        return orderIdGenerate(shopId,1);
    }






}
