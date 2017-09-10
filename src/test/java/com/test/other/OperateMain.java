package com.test.other;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.bean.SendMail;
import com.dsm.common.utils.MailUtil;
import com.dsm.model.cart.ShoppingCartItem;
import com.dsm.model.user.User;
import org.junit.Test;

import java.util.Arrays;

public class OperateMain {
	public static void main(String[] args) {
		SendMail sendmail = new SendMail();
        sendmail.setSubject("这里是邮件的标题");//邮件标题  
        sendmail.setContent("测试邮件发送"); //邮件内容
        //设置附件
//        String[] exFile = "e:\\123.zip;d:\\123.zip".split(";");
//        System.out.println(Arrays.toString(exFile));
//        for (int i = 0; i < exFile.length; i++) {
//        	sendmail.attachFile(exFile[i]); //邮件附件（绝对路径）
//		}
        sendmail.setReceiver("707076097@qq.com");//接收者
        System.out.println(sendmail);
        MailUtil.sendMail(sendmail);
//        SendMail sendmail = new SendMail(); 
//        System.out.println(sendmail);
//        sendmail.setSubject(args[0]);//邮件标题  
//        sendmail.setContent(args[1]); //邮件内容
//        //设置附件
//        String[] exFile = args[2].split(";");
//        for (int i = 0; i < exFile.length; i++) {
//        	sendmail.attachFile(exFile[i]); //邮件附件（绝对路径）
//        }
//        sendmail.setReceiver(args[3]);//接收者  
//        MailUtil.sendMail(sendmail);  
	}

    @Test
    public void test1(){
        System.out.println(JSONObject.parseObject(null, ShoppingCartItem.class));
    }

    @Test
    public void test2(){
        System.out.println(JSONObject.toJSONString("123123123"));
    }

    @Test
    public void test3(){
        User[] u = new User[2];
        u[0] = new User(1,"111");
        u[1] = new User(2,"222");

        String[] o = Arrays.stream(u).map(JSONObject::toJSONString).toArray(String[]::new);
//
//
        System.out.println(Arrays.toString(o));
    }

    @Test
    public void test4(){
        System.out.println(JSONObject.parse("123123123"));
    }
}
