package com.test.daoTest;

import com.dsm.dao.IProductDao;
import com.dsm.dao.IProductSkuDao;
import com.dsm.model.product.Sku;
import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/8
 *
 * @author : Lbwwz
 * <p/>
 * @see {@link IProductDao}
 * 商品相关操作的dao测试类
 */
public class ProductSkuDaoTest extends BaseJunitTest{
    @Autowired
    private IProductSkuDao productSkuDao;

    @Test
    public void addSkuItemListTest(){
        List<Sku> list = new ArrayList<>();
        Sku sku1 = new Sku();
        sku1.setProductId(1);
        sku1.setProperties("123");
        sku1.setPropertiesName("456");
        sku1.setQuantity(123);
        sku1.setShopSn("123123123123");
        sku1.setSkuPrice(new BigDecimal(123.3));
        Sku sku2 = new Sku();
        sku2.setProductId(1);
        sku2.setProperties("222");
        sku2.setPropertiesName("333");
        sku2.setQuantity(1444);
        sku2.setShopSn("11111111");
        sku2.setSkuPrice(new BigDecimal(11111));
//        list.add(sku1);
//        list.add(sku2);
        productSkuDao.addSkuItemList(list);
    }

    @Test
    public void addSkuItemTest(){
        Sku sku1 = new Sku();
        sku1.setProductId(1);
        sku1.setProperties("123");
        sku1.setPropertiesName("456");
        sku1.setQuantity(123);
        sku1.setShopSn("123123123123");
        sku1.setSkuPrice(new BigDecimal(123.3));
        productSkuDao.addSkuItem(sku1);
    }


    @Test
    public void getSkuListByProductIdTest(){
        System.out.println(productSkuDao.getSkuListByProductId(1));
    }


}
