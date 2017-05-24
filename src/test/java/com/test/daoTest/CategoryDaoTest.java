package com.test.daoTest;

import com.dsm.dao.ICategoryDao;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/22
 *
 * @author : Lbwwz
 */
public class CategoryDaoTest extends BaseJunitTest {

    @Resource
    private ICategoryDao categoryDao;

    @Test
    public void getTreeCategoryListTest(){
        System.out.println(categoryDao.getTreeCategoryList(1));
    }
}
