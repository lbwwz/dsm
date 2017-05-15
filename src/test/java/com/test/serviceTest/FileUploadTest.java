package com.test.serviceTest;

import com.dsm.dao.IImageDao;
import com.dsm.model.ImageBean;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/15
 *
 * @author : Lbwwz
 */
public class FileUploadTest extends BaseJunitTest {
    @Resource
    private IImageDao iImageDao;

    @Test
    public void test(){
        iImageDao.addImage(new ImageBean(1,"123",null,new Long(123123),1));

    }
}
