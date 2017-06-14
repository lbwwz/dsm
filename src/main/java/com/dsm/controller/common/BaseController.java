package com.dsm.controller.common;

import com.dsm.model.user.User;
import com.dsm.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.function.Consumer;

public abstract class BaseController {

    /**
     * 默认登录设定的用户
     */
    @Autowired
    private IUserService userService;
    @ModelAttribute
    public void testLogin(){
        User user = getSessionUser();
        if(user == null){
            userService.userLogin("18202755619", "123456", false);
        }
    }

    /**
     * 获取request
     *
     * @return 该次请求线程对应的 request对象
     */
    protected HttpServletRequest getRequest() {
        return RequestResponseContext.getRequest();
    }

    /**
     * 获取response
     *
     * @return 该次请求线程对应的 response对象
     */
    protected HttpServletResponse getResponse() {
        return RequestResponseContext.getResponse();
    }

    /**
     * 获取session
     *
     * @return 该用户此次交互的 session对象
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取站点的web路径
     *
     * @return http://(ip地址+端口号）/(站点名)； 如：http://localhost
     */
    protected String getWebRoot() {
        HttpServletRequest request = getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * 重置session中的属性对象信息
     *
     * @param attributeName 属性名称
     * @param attributeObj  属性名称对应的属性对象
     */
    @SuppressWarnings("unused")
    protected <T> void resetSessionAttribute(String attributeName, T attributeObj) {
        getSession().setAttribute(attributeName, attributeObj);
    }

    /**
     * 更新session中的属性信息,
     *
     * @param attribute session 中的属性名
     * @param consumer 操作方法，接收的参数是名叫 attribute 的属性值
     * @param <T> 返回值额泛型类型
     */
    @SuppressWarnings("unchecked")
    protected <T> void updateSessionAttribute(String attribute, Consumer<T> consumer) {
        consumer.accept((T) getSession().getAttribute(attribute));
    }


    @SuppressWarnings("unchecked")
    protected <T> T getSessionAttr(String attributeName, Class<T> clazz) {
        return getSession().getAttribute(attributeName) == null ? null : (T) getSession().getAttribute(attributeName);
    }

    /**
     * 获取sessionUser信息
s     *
     * @return {@link User}
     */
    protected User getSessionUser() {
        return getSession().getAttribute("user") == null ? null : (User) getSession().getAttribute("user");
    }
}
