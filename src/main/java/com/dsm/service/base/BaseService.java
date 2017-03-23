package com.dsm.service.base;

import com.dsm.model.user.User;
import org.apache.shiro.SecurityUtils;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/28
 *
 * @author : Lbwwz
 */
public class BaseService {

    /**
     * 事务超时相应时间限制
     */
    protected final long timeOut = 5000;

    /**
     * 获取 sessionUser 对象
     *
     * @return 当前用户在session中的用户信息
     */
    protected User getSessionUser() {
        return getSessionObject("user");
    }

    /**
     * 获取session中某个属性的对象信息
     *
     * @param attributeName 对象所对应session中属性名
     * @param <T>           对象类型
     * @return 对象信息
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSessionObject(String attributeName) {
        return (T) SecurityUtils.getSubject().getSession().getAttribute(attributeName);
    }
}
