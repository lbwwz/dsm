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

        BaseAttrBean bean1 = new BaseAttrBean(1,"",21,null,1);
        BaseAttrBean bean2 = new BaseAttrBean(1,"",4,null,1);
        List<BaseAttrBean> list = new ArrayList<>();
        list.add(bean1);
        list.add(bean2);
        list = list.subList(0,0);
        System.out.println(productDao.getPageByCategoryWithWeightValue(1,0,6,list,"0.05","1","1","1","0"));
    }
    @Test
    public void getPageByCategoryByPriceTest() {
        System.out.println(productDao.getPageByCategoryWithPrice(1,0,12,0, null));
    }

    @Test
    public void getPageByCategoryByPriceNewTest() {
        System.out.println(productDao.getPageByCategoryWithPriceNew(1,1500,1, null));
    }

    @Test
    public void getPageByCategoryWithWeightValueNewTest() {
        System.out.println(productDao.getPageByCategoryWithWeightValueNew(1,1500,null,"0.05","1","1","1","0"));
    }


}
