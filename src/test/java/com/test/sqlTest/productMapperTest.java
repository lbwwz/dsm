package com.test.sqlTest;

import com.dsm.model.product.ProductBean;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/7
 *
 * @author : Lbwwz
 */
public class productMapperTest extends BaseMapperTest{

    @Test
    public void test(){
        String statement = "com.dsm.dao.IProductDao.getProduct";//映射sql的标识字符串


        //执行查询返回一个唯一user对象的sql
        List<ProductBean> li=  session.selectList(statement,1);
        System.out.println("打印对象："+li);
    }

    @Test
    public void test2(){
        String statement = "com.dsm.dao.IProductDao.addProductInfo";
        ProductBean bean = new ProductBean();
        bean.setProductName("sansumg");
        bean.setMainImage("123123123123123123123123.jpg");
        bean.setCatId(14);
        bean.setBrandId(1);
        bean.setShopId(1);
        bean.setMaxPrice(BigDecimal.valueOf(100));
        bean.setMinPrice(BigDecimal.valueOf(10));
        bean.setKeywords("123");
        bean.setProductDesc("<img src='123'/>");
        session.insert(statement,bean);
    }
}
