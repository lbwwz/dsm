package com.test.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/8
 *
 * @author : Lbwwz
 * <p/>
 * spring测试类公共实现
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring-applicationContext.xml"})
public class BaseJunitTest {
}
