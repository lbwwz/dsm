package com.test.other;
import com.dsm.common.bean.SendMail;
import com.dsm.common.utils.MailUtil;

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
}
