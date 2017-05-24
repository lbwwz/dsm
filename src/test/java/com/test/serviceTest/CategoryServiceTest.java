package com.test.serviceTest;

import com.dsm.service.interfaces.ICategoryService;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/19
 *
 * @author : Lbwwz
 */
public class CategoryServiceTest extends BaseJunitTest{

    @Resource
    ICategoryService categoryService;

    @Test
    public void getLevelCatalogTest(){
        System.out.println(categoryService.getLevelCatalog(14));
    }
    @Test
    public void getCatalogNavListTest(){
        System.out.println(categoryService.getCatalogNavList(14));
    }

    @Test
    public void getLeafCatalogListTest(){
        System.out.println(categoryService.getLeafCateLogList(1));
    }


}
