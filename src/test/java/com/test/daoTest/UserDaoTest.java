package com.test.daoTest;

import com.dsm.dao.IUserDao;
import com.dsm.model.address.ShippingAddress;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.IShippingAddressService;
import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Autowired
    private IShippingAddressService addressService;

    @Test
    public void test(){
        User user = new User();
        user.setMobile("18202755619");

        user = userDao.queryUserByLogin(user);
        System.out.println(user);
    }

    @Test
    public void test2(){
        List<ShippingAddress> addresses =  addressService.getConsigneeAddressList();
        System.out.println(addresses);
    }

}
