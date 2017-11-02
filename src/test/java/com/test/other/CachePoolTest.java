package com.test.other;


import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.model.user.User;
import com.test.common.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lbwwz on 2016/8/17.
 */
public class CachePoolTest extends BaseJunitTest {


    @Resource
    private IRedisService redisService;


    @Test
    public void Test1() {

        Map<String, String> map = redisService.getHsetAll("test7");

        System.out.println(map);
    }

    @Test
    public void Test2() {
        System.out.println(redisService.exists("test1"));
    }

    @Test
    public void Test3() {

        Map<String, String> m = new HashMap<>();

        m.put("aa", "123");
        m.put("ww", "456");
        m.put("ee", "789");
        System.out.println(redisService.setHmset("test8", m));
    }

    @Test
    public void Test4() {

        System.out.println(redisService.expire("test8", DsmConcepts.MINUTE));
    }

    @Test
    public void Test5() {
        Object o = redisService.getHSet("test8", "sd");
        System.out.println(o);
    }


    @Test
    public void Test6() {
        User[] u = new User[2];
        u[0] = new User(1, "111");
        u[1] = new User(2, "222");
        redisService.lpush("test111", u);
    }

    @Test
    public void Test7() {
        List<User> u = new ArrayList<>();
        u.add(new User(1, "111"));
        u.add(new User(2, "222"));
        redisService.lpush("test222", u);
    }


    @Test
    public void Test9() {
        String a = "1231321231231";
        System.out.println(a.split("#").length);
    }


    @Test
    public void Test10() {
        System.out.println(redisService.lpop("test1"));
    }


    @Test
    public void Test11() {

        String key = "user";
        String orderHashCode = "4";
        String info = "1232312";
        long size = 3;

        redisService.setHSet(key, orderHashCode, JSONObject.toJSONString(info) + DsmConcepts.EXPIRE_TIME_SEPARATE + System.currentTimeMillis());
        String tempKey= key + "_affiliate";
        redisService.removeListValue(tempKey,1,orderHashCode);
        redisService.lpush(tempKey, orderHashCode);
        if (redisService.listLen(tempKey) > size) {
            String temp = redisService.rpop(tempKey);
            redisService.delHSet(key, temp);
        }


    }

    @Test
    public void test12(){
        redisService.removeListValue("user__affiliate",1,"1");
    }
}
