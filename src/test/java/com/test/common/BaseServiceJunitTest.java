package com.test.common;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/8
 *
 * @author : Lbwwz
 */
public class BaseServiceJunitTest extends BaseJunitTest {
    // 模拟request,response
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;

    // 执行测试方法之前初始化模拟request,response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

}
