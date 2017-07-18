package com.test.daoTest;

import com.dsm.dao.IUserDao;
import com.dsm.model.user.User;
import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/7/16
 *
 * @author : Lbwwz
 */
public class UserDaoTest extends BaseJunitTest{
    @Autowired
    private IUserDao userDao;

    @Test
    public void test(){
        User user = new User();
        user.setMobile("18202755619");

        user = userDao.queryUserByLogin(user);
        System.out.println(user);
    }
}
