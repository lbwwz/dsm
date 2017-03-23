/**
 * Created by Lbwwz on 2016/9/22.
 *
 * 相关的校验工具
 */

//window.onload = function () {

    //校验电话号码
    function checkMobile(mobile) {
        var regex = /^[1][34578]\d{9}$/;
        return pattern(regex, mobile);
    }

    //校验是否是邮箱
    function checkEmail(email) {
        var regex = /^\w+[a-z0-9A-Z]@[a-z0-9A-Z]+(\.[a-z0-9A-Z]+){1,3}$/;
        return pattern(regex, email);
    }

    //校验是否是邮政编码
    function checkZipCode(zipcode) {
        var regex = /^\d{6}$/;
        return pattern(regex, zipcode);
    }

    //校验是否是身份证号码
    function checkIDNum(IDNum){
        var regex;
        if (IDNum.length() == 18) {
            regex = /^[1-9]\\d{5}(19\\d{2}|20\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9xX]$/;
        } else if (IDNum.length() == 15) {
            regex = /^[1-9]\\d{7}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}$/;
        } else {
            return false;
        }
        return pattern(regex,IDNum);
    }

    //校验密码是否是8-16位数字字母组合
    function checkPassWord_8_16(password){
        var regex = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
        return pattern(regex, password);
    }

    /**
     * 正则表达式校验数据的方法
     * @param regex 校验规则（正则表达式）
     * @param checkObj 校验的字符串
     * @returns {boolean}
     */
    function pattern(regex, checkObj) {
        if ($.trim(checkObj).match(regex) == null) {
            return false;
        }
        return true;
    }
//}
