package com.test.daoTest;

import com.dsm.dao.ICartDao;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/8/14
 *
 * @author : Lbwwz
 */
public class CartDaoTest extends BaseJunitTest {

    @Resource
    private ICartDao cartDao;

    @Test
    public void getShoppingCartItemTest(){
        System.out.println(cartDao.getShoppingCartItem(1));

    }

    @Test
    public void getShoppingCartInfoTest(){
        System.out.println(cartDao.getShoppingCartInfo(1));
    }
}
