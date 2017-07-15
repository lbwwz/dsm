package com.test.springTest;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/7/10
 *
 * @author : Lbwwz
 */
public class SpringTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("application-context.xml");
        applicationContext.start();
        applicationContext.close();
    }
    @Test
    public  void test2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("application-context.xml");
        applicationContext.getBean("xtayfjpk");
        applicationContext.close();
    }
//    @Test
//    public  void test3(String[] args) {
//        GenericApplicationContext parent = new GenericApplicationContext();
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setParent(parent);
//        context.refresh();
//        context.start();
//        context.close();
//    }
}
