package com.test.serviceTest;

import com.dsm.service.base.IBusinessCacheService;
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
public class BusinessCacheServiceTest extends BaseJunitTest{

    @Resource
    IBusinessCacheService businessCacheService;

    @Test
    public void getProductSkuItemListFromCacheTest(){
        System.out.println(businessCacheService.getProductSkuItemListFromCache(false,1L,null,15L));
    }



}
