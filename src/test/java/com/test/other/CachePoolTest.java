package com.test.other;


import com.dsm.common.cache.cacheService.IRedisService;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Lbwwz on 2016/8/17.
 */
public class CachePoolTest extends BaseJunitTest{


    @Resource
    private IRedisService redisService;


    @Test
    public void Test1(){

        Map<String, String> map = redisService.getHSetAll("test7");

        System.out.println(map);
    }

    @Test
    public void Test2(){
        System.out.println(redisService.exists("test1"));
    }
}
