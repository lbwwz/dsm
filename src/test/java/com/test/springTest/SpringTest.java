package com.test.springTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/7/10
 *
 * @author : Lbwwz
 */
@Controller
public class SpringTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("application-context.xml");
        //这里没有调用 refresh() 方法刷新 spring 上下文
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
    @Test
    public  void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/spring-applicationContext.xml");

        Object o = context.getBean("IProductDao");

    }

    @Test
    public void test4() {
        AnnotatedGenericBeanDefinition beanDefinition=new AnnotatedGenericBeanDefinition(SpringTest.class);
        System.out.println(beanDefinition.getMetadata().getAnnotationTypes());
        System.out.println(beanDefinition.isSingleton());
        System.out.println(beanDefinition.getBeanClassName());
    }

    @Test
    public void test5(){
        ClassPathScanningCandidateComponentProvider provider=new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> definitionSet= provider.findCandidateComponents("com.dsm.controller");
        System.out.println(definitionSet.size());
    }

    @Test
    public void test6(){
        SystemEnvironmentPropertySource source =
                new SystemEnvironmentPropertySource("systemEnvironment",(Map) System.getenv());
        System.out.println(source.getProperty("PROCESSOR_LEVEL"));
        System.out.println(source.getProperty("PROCESSOR_LEVEL".toLowerCase()));
        System.out.println(source.getProperty("PROCESSOR.LEVEL"));
    }


    public static void main(String[] args) throws ClassNotFoundException {
        System.setProperty("spring.profiles.active","dev");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringTest.class);
        System.out.println(Arrays.asList(context.getBeanNamesForType(String.class)));
    }


    /**
     * profile，剖面，大体意思是：我们程序可能从某几个剖面来执行应用，比如正式机环境、测试机环境、开发机环境等.
     * 每个剖面的配置可能不一样（比如开发机可能使用本地的数据库测试，正式机使用正式机的数据库测试）等；
     * @return
     */
    @Bean
    @Profile("test")
    public String str1() {
        return "str1";
    }

    @Bean
    @Profile("dev")
    public String str2() {
        return "str2";
    }

    @Bean
    public String str3() {
        return "str3";
    }

}
