package com.test.serviceTest;

import com.dsm.model.user.User;
import com.dsm.service.interfaces.IUserService;
import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/8
 *
 * @author : Lbwwz
 * <p/>
 * userService测试类
 */
public class UserServiceTest extends BaseJunitTest{

    @Autowired
    private IUserService userService;

    @Test
    public void getUser(){
        User user = userService.queryUserById(1);
        System.out.println(user);
    }
}
