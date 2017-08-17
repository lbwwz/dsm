package com.test.other;


import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lbwwz on 2016/8/17.
 */
public class CachePoolTest extends BaseJunitTest{


    @Resource
    private IRedisService redisService;


    @Test
    public void Test1(){

        Map<String, String> map = redisService.getHsetAll("test7");

        System.out.println(map);
    }

    @Test
    public void Test2(){
        System.out.println(redisService.exists("test1"));
    }

    @Test
    public void Test3(){

        Map<String, String > m = new HashMap<>();

        m.put("aa","123");
        m.put("ww","456");
        m.put("ee","789");
        System.out.println(redisService.setHmset("test8",m));
    }

    @Test
    public void Test4(){

        System.out.println(redisService.expire("test8", DsmConcepts.MINUTE));
    }
}
