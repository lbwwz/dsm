package com.dsm.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 自定义加密算法工具类 使用Java自带的MessageDigest类
 *
 * @author think
 */
public class EncryptUtils {

    private static final String KEY_SHA = "SHA";
    private static final String KEY_MD5 = "MD5";

    /**
     * 构造方法私有化，防止实例化。
     */
    private EncryptUtils(){}


    /*===== 单项不可逆加密 =====*/

    /**
     * MD5加密字符串
     *
     * @param data String
     * @return 使用md5加密后的字符串
     */
    public static String encryptMD5(String data) {
        try {
            // 将字符串按照utf-8编码
            return changeToString(encryptMD5(data.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码格式不存在");
        }
    }

    /**
     * SHA加密字符串
     *
     * @param data String
     * @return 使用sha加密后的字符串
     */
    @SuppressWarnings("unused")
    public static String encryptSHA(String data) {
        try {
            // 将字符串按照utf-8编码
            return changeToString(encryptSHA(data.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码格式不存在");
        }
    }

    /**
     * MD5加密字节数组
     *
     * @param data 待加密字节数组
     * @return 使用md5加密后的字节数组
     */
    public static byte[] encryptMD5(byte[] data) {

        return getEncryptBytes(data, KEY_MD5);
    }

    /**
     * SHA加密
     *
     * @param data 待加密字节数组
     * @return 使用sha加密后的字节数组
     */
    public static byte[] encryptSHA(byte[] data) {

        return getEncryptBytes(data, KEY_SHA);
    }


    /**
     * 将字节数组加密
     *
     * @param data        要加密的字节数组
     * @param encryptKey 加密方式
     * @return 使用指定方法加密后的字节数组
     */
    private static byte[] getEncryptBytes(byte[] data, String encryptKey) {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance(encryptKey);
            sha.update(data);

            return sha.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("编码错误");
        }
    }

    /**
     * 将字节数组处理，转化为字符串
     *
     * @param data 字节数组
     * @return 转化之后的字符数组
     */
    private static String changeToString(byte[] data) {

        StringBuilder strBuffer = new StringBuilder();
        for (byte aData : data) {
            strBuffer.append(Integer.toHexString(0xff & aData));
        }
        return strBuffer.toString();
    }


    /*======== 关于base64的相关操作 ========*/

    /**
     * Base64编码。将字节数组中字节3个一组编码成4个可见字符。
     *
     * @param b
     *            需要被编码的字节数据。
     * @return 编码后的Base64字符串。
     */
    public static String encodeBase64(byte[] b)
    {
        if(b == null){
            return null;
        }else if(b.length==0){
            return "";
        }
        return (new BASE64Encoder()).encode(b);
    }

    /**
     * Base64解码。
     *
     * @param code
     *            用Base64编码的ASCII字符串
     * @return 解码后的字节数据
     */
    public static byte[] decodeBase64(String code)
    {
        // 检查参数合法性
        if (code != null){
            try {
                return (new BASE64Decoder()).decodeBuffer(code);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}