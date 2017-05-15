package com.dsm.common.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/13
 *
 * @author : Lbwwz
 */
public class SessionManagerListener implements HttpSessionListener, HttpSessionIdListener {

    /**
     * sessionid变更时被调用？具体情况未知
     * @param event
     * @param oldSessionId
     */
    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        System.out.println(System.currentTimeMillis() + ": Session " + oldSessionId + " changed to " + event.getSession().getId());
        System.out.println("###########################session变更！");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("###########################session创建！");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //这里做文件上传中未引用文件的移除
        HttpSession session = se.getSession();
        removeUnusedFile(session);


        System.out.println("###########################session失效！"+se.getSession().getAttribute("user"));
    }

    /**
     * 移除文件上传中未保存的文件
     */
    private void removeUnusedFile(HttpSession session) {


    }
}
