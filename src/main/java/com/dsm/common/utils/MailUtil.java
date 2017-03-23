package com.dsm.common.utils;

import com.dsm.common.bean.SendMail;
import com.dsm.common.utils.configContext.ConfigContext;
import com.dsm.common.utils.configContext.ConfigContextFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Properties;


/**
 * 邮件操作的方法
 * @author Think
 *
 */
public class MailUtil {

    //邮件资源文件的config 对象
    private static ConfigContext emailSettings;

    //初始化获取配置文件
    static {
        try {
            emailSettings = ConfigContextFactory.CreateContext("properties/email.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key获取对应的值
     * @param key 键
     */
    public static String get(String key){
        return emailSettings.getConfigVal(key);
    }




    /**
     * 方法说明：把主题转换为中文 
     * 输入参数：String strText
     * 返回类型： 
     */  
    public static String transferChinese(String strText) {  
        try {  
            strText = MimeUtility.encodeText(strText);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return strText;  
    }  
    
    /**  
     * 方法说明：发送邮件(执行邮件发送的方法)
     * 输入参数：邮件对象 
     * 返回类型：boolean 成功为true，反之为false 
     */  
    public static boolean sendMail(final SendMail mail) {
  
        // 构造mail session  
        Properties props = System.getProperties(); 
        props.put("mail.smtp.host", mail.getHost());  
        props.put("mail.smtp.auth", get("mail.smtp.auth"));
		
		Session session = Session.getDefaultInstance(props,  
                new Authenticator() {  
                    public PasswordAuthentication getPasswordAuthentication() {  
                        return new PasswordAuthentication(mail.getUsername(), mail.getPassword());  
                    }  
                });
		
		try {  
            // 构造MimeMessage 并设定基本的值  
            MimeMessage msg = new MimeMessage(session);  
            msg.setFrom(new InternetAddress(mail.getFrom()));  
            //设置邮件的接收方
            
            // 获取抄送者信息
            String[] addresses = mail.getReceiver();
            if (addresses != null){
                // 为每个邮件接收者创建一个地址
            	InternetAddress[] ccAdresses = new InternetAddress[addresses.length];
                for (int i=0; i<addresses.length; i++){
                    ccAdresses[i] = new InternetAddress(addresses[i]);
                }
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                msg.setRecipients(Message.RecipientType.TO, ccAdresses);  
            } 
            
            
           
            msg.setSubject(transferChinese(mail.getSubject()));  
  
            // 构造 Multipart
            Multipart mp = new MimeMultipart();  
  
            // 向 Multipart 添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();  
            mbpContent.setText(mail.getContent());  
            // 向 MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);  
  
            // 向 Multipart添加附件
            for (File file : mail.getFile()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                String filename = file.toString();
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                mbpFile.setFileName(fds.getName());
                // 向 MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }  
  
            mail.getFile().clear();
            // 向 Multipart 添加 MimeMessage
            msg.setContent(mp);  
            msg.setSentDate(new Date());  
            // 发送邮件  
            Transport.send(msg);  
  
        } catch (MessagingException mex) {  
            mex.printStackTrace();  
            Exception ex ;
            if ((ex = mex.getNextException()) != null) {  
                ex.printStackTrace();  
            }  
            return false;  
        } 
        return true;  
    }  
}
