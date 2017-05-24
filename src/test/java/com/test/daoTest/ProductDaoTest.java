package com.test.daoTest;

import com.dsm.dao.IProductDao;
import com.dsm.model.product.*;
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
        System.out.println(productDao.getProductBaseInfoById(1));
    }

    @Test
    public void getProductDetailInfoTest(){
        ProductDetail detail = productDao.getProductDetailInfo(15);
        System.out.println(detail);
    }

    @Test
    public void addProductListTest() {

        List<ProductImageItem> imageList = new ArrayList<>();

        imageList.add(new ProductImageItem(1,"11111",1));
        imageList.add(new ProductImageItem(1,"222",0));
        productDao.addProductImageList(imageList);
    }

    @Test
    public void addGraphicDetailTest() {
        productDao.addGraphicDetail(new GraphicDetail(1,null));
    }


    @Test
    public void getPageByCategoryWithWeightedTest() {
        System.out.println(productDao.getPageByCategoryWithWeighted(1,2,2,"0.05","1","1","1","0"));
    }
    @Test
    public void getPageByCategoryByPriceTest() {
        System.out.println(productDao.getPageByCategoryByPrice(1,0,2,1));
    }

}
