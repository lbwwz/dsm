package com.test.other;


import com.dsm.common.utils.customCache.CacheExecutor;
import com.dsm.common.utils.customCache.CachePool;
import org.junit.Test;

/**
 * Created by Lbwwz on 2016/8/17.
 */
public class CachePoolTest {

    @Test
    public void test2(){
        CachePool pool = CacheExecutor.newCachePool();
    }
}
