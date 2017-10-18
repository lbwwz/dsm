package com.test.daoTest;

import com.dsm.dao.ICartDao;
import com.dsm.model.cart.ShoppingCartItemPO;
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
    public void getShoppingCartItemTest() {
        System.out.println(cartDao.getShoppingCartItem(1));
    }

    @Test
    public void getShoppingCartInfoTest() {
        System.out.println(cartDao.getShoppingCartInfoAll(1));
    }

    @Test
    public void updateCartItemTest() {
        ShoppingCartItemPO scPO = new ShoppingCartItemPO();
        scPO.setUserId(1L);
        scPO.setShopId(1);
        scPO.setIsSelected(0);
        scPO.setCartItemNum(10);
        long i = cartDao.updateCartItem(scPO);
        System.out.println(i);
    }

    @Test
    public void addCartItemTest() {
        ShoppingCartItemPO scPO = new ShoppingCartItemPO();
        scPO.setUserId(1L);
        scPO.setSkuId(1);
        scPO.setShopId(1);
        scPO.setIsSelected(0);
        scPO.setCartItemNum(10);
        long i = cartDao.addCartItem(scPO);
        System.out.println(scPO);
    }

    @Test
    public void deleteCartItemTest(){
        cartDao.deleteCartItem(5);
    }

}
