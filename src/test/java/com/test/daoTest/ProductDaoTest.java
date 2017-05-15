package com.test.daoTest;

import com.dsm.dao.IProductDao;
import com.dsm.model.product.ProductBean;
import com.dsm.model.product.ProductDetail;
import com.dsm.model.product.ProductDetailAttrInfo;
import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
public class ProductDaoTest extends BaseJunitTest{
    @Autowired
    private IProductDao productDao;

    @Test
    public void addProductInfo(){
        ProductBean productBean = new ProductBean("123",
                "1.jpg","好吃点蛋卷",12,
                14, 1);

        productDao.addProductInfo(productBean);
    }
    @Test
    public void addProductCustomAttrListTest(){
        ProductDetailAttrInfo info = new ProductDetailAttrInfo();

        info.setProductId(1);
        info.setAttrName("123");
        info.setAttrValue("qwe");

        List<ProductDetailAttrInfo> list = new ArrayList<>();
        list.add(info);
        info = new ProductDetailAttrInfo();

        info.setProductId(1);
        info.setAttrName("www");
        info.setAttrValue("aaasssasas");
        list.add(info);


        productDao.addProductCustomAttrList(list);
    }

    @Test
    public void getProductTest(){
        System.out.println(productDao.getProductBaseInfo(5));
    }

    @Test
    public void getProductDetailInfoTest(){
        ProductDetail detail = productDao.getProductDetailInfo(1);
        System.out.println(detail);
    }


}
