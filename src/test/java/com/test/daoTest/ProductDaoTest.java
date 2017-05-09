package com.test.daoTest;

import com.dsm.dao.IProductDao;
import com.dsm.model.product.ProductBean;
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
 * @see {@link IProductDao}
 * 商品相关操作的dao测试类
 */
public class ProductDaoTest extends BaseJunitTest{
    @Autowired
    private IProductDao productDao;

    @Test
    public void addProductInfo(){
        ProductBean productBean = new ProductBean("123","",
                "1.jpg","好吃点蛋卷",12,
                14, 1);

        productDao.addProductInfo(productBean);
    }

    @Test
    public void getProductTest(){
        System.out.println(productDao.getProductBaseInfo(5));
    }

    @Test
    public void getProductDetailInfoTest(){
        System.out.println(productDao.getProductDetailInfo(1));
    }


}
