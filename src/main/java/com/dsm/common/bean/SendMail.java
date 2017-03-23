package com.dsm.common.bean;

import com.dsm.common.utils.MailUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/25
 *
 * @author : Lbwwz
 * @version 1.0
 *          发送邮件的邮件信息封装类
 *          Title: 使用javamail发送邮件,支持邮件群发
 *          Description: 演示如何使用javamail包发送电子邮件。这个实例可发送多附件
 */
public class SendMail {


    private String[] receiver;      // 收件人
    private String from = "";       // 发件人
    private String host = "";       // smtp主机
    private String username = "";   // 邮件用户名
    private String password = "";   // 邮件密码
    private String filename = "";   // 附件文件名
    private String subject = "";    // 邮件主题
    private String content = "";    // 邮件正文
    private List<File> file = Collections.synchronizedList(new ArrayList<>());// (线程安全的)附件文件集合

    /**
     * 初始化设置对应的发送端信息
     */
    private void init() {
        //设置邮箱服务器
        this.host = MailUtil.get("mail.smtp.host");
        //设置发送方
        this.username = MailUtil.get("mail.host.username");
        this.from = MailUtil.get("mail.host.username");
        //设置发送方密码
        this.password = MailUtil.get("mail.host.password");

    }

    /**
     * <br>
     * 方法说明：默认构造器 <br>
     */
    public SendMail() {
        init();
    }

    /**
     * <br>
     * 方法说明：构造器，提供直接的参数传入 <br>
     *
     * @param receiver   接收人
     * @param from       发件人
     * @param smtpServer 邮件服务器
     * @param username   邮件登录名
     * @param password   邮件密码
     * @param subject    邮件主题
     * @param content    邮件内容
     */
    public SendMail(String[] receiver, String from, String smtpServer, String username,
                    String password, String subject, String content) {
        this.receiver = receiver;
        this.from = from;
        this.host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
        init();
    }

    /**
     * <br>
     * 方法说明：设置邮件服务器地址 <br>
     *
     * @param host String host 邮件服务器地址名称 <br>
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * <br>
     * 方法说明：设置登录服务器校验密码 <br>
     *
     * @param pwd 密码
     */
    public void setPassWord(String pwd) {
        this.password = pwd;
    }

    /**
     * <br>
     * 方法说明：设置登录服务器校验用户 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setUserName(String usn) {
        this.username = usn;
    }

    /**
     * <br>
     * 方法说明：设置邮件发送目的邮箱 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver.split(";");
    }

    /**
     * <br>
     * 方法说明：设置邮件发送源邮箱 <br>
     * 输入参数：邮件的接收方
     * 返回类型：
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * 方法说明：设置邮件主题
     * 输入参数：主题内容
     * 返回类型：
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * <br>
     * 方法说明：设置邮件内容 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * <br>
     * 方法说明：往附件组合中添加附件 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void attachFile(String fname) {
        file.add(new File(fname));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilename() {
        return filename;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public String[] getReceiver() {
        return receiver;
    }

    public String getFrom() {
        return from;
    }

    public String getHost() {
        return host;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SendMail [receiver=" + Arrays.toString(receiver) + ", from=" + from + ", host="
                + host + ", username=" + username + ", password=" + password
                + ", filename=" + filename + ", subject=" + subject
                + ", content=" + content + ", file=" + file + "]";
    }


}