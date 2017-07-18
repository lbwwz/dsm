package com.dsm.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

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

    /**
     * 为message加盐
     * @param message
     * @param salt
     * @return
     */
    public static String encryptWithSalt(String message, String salt,String encryptType){
        Object saltPwd = new SimpleHash(encryptType,message, ByteSource.Util.bytes(salt),1024);
        return saltPwd.toString();
    }
}
