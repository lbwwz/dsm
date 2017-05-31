package com.test.daoTest;

import com.dsm.dao.IProductAttrDao;
import com.dsm.model.product.ProductAttrBean;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/26
 *
 * @author : Lbwwz
 */
public class ProductAttrDaoTest extends BaseJunitTest{
    @Resource
    private IProductAttrDao productAttrDao;

    @Test
    public void getKeyAttrInfoListTest(){
        List<ProductAttrBean> list =  productAttrDao.getKeyAttrInfoList(14);
        System.out.println(list);
    }
    @Test
    public void getAttrByCatTest(){
        List<ProductAttrBean> list =  productAttrDao.getAttrByCat(14);
        System.out.println(list);
    }

}
