package com.test.serviceTest;

import com.dsm.service.interfaces.IProductService;
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
 * productService测试类
 */
public class ProductServiceTest extends BaseJunitTest{

    @Autowired
    private IProductService productService;

    @Test
    public void releaseProductTest(){

        productService.releaseProduct(null);
    }

}
