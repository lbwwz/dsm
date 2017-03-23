package com.dsm.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定义一些字符串检测的工具方法
 *
 * @author lbwwz
 */
public class ValidateUtils {

    /**
     * 判断一个字符串是否是邮箱帐号
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        email = email.trim();

		/*
         * 定义邮箱匹配规则
		 * 1. @之前可以为任意 \w 字符，但是紧接着@的字符必须是 [a-z0-9A-Z]
		 * 2. @之后到第一个"."之间只能是 [a-z0-9A-Z]
		 * 3. 2之后的格式为 ".edu.cn"之类的格式但是不超过三个长度
		 */
        //由规则知最小长度不可能小于5
        if (email == null || email.length() < 5) {
            return false;
        }

        final String regex = "^\\w+[a-z0-9A-Z]@[a-z0-9A-Z]+(\\.[a-z0-9A-Z]+){1,3}$";
        return email.matches(regex);
    }

    /**
     * 判断一个字符串是否是手机电话号码（不含区号）
     *
     * @param tel
     * @return
     */
    public static boolean isMobile(String tel) {
        tel = tel.trim();
        /*
		 * 定义国内电话号码的匹配规则
		 * 1. 前两位开头的只有：13 14 15 18
		 * 2. 后面是九位数字
		 */
        final String regex = "[1][34578]\\d{9}";
        return tel.matches(regex);
    }
    /**
     * 判断一个字符串是否是邮政编码（不含区号）
     *
     * @param zipcode
     * @return
     */
    public static boolean isZipCode(String zipcode) {
        zipcode = zipcode.trim();

        final String regex = "\\d{6}";
        return zipcode.matches(regex);
    }

    /**
     * 判断一个字符串是不是身份证号码
     *
     * @param idCardNum
     * @return
     */
    public static boolean isIdCardNum(String idCardNum) {
        String regex;
        if (idCardNum.length() == 18) {
            regex = "^[1-9]\\d{5}(19\\d{2}|20\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9xX]$";
        } else if (idCardNum.length() == 15) {
            regex = "^[1-9]\\d{7}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}$";
        } else {
            return false;
        }
        return idCardNum.matches(regex);
    }


    /**
     * 判断字符串是否是一串数字
     *
     * @param num
     * @return
     */
    public static boolean isNumbers(String num) {
		/*
		 * 利用数字类型转换异常的捕获确定是否是数字
		 */
        try {
            Long.parseLong(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 校验是否含特殊字符
     *
     * @param str
     * @return
     */
    public static boolean isContainSpecialChar(String str) {
        Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]", Pattern.MULTILINE);
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 校验密码是否是8-16位的数字和字母组合
     * @param password
     * @return
     */
    public static boolean isRightfulPassword(String password) {

        final String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return password.matches(regex);
    }

}
