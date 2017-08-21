package com.dsm.common.listener;

import com.dsm.common.annotation.TestAnnotation;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/23
 *
 * @author : Lbwwz
 */
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 根容器为Spring容器
        if(event.getApplicationContext().getParent()==null){
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(TestAnnotation.class);
            for(Object bean:beans.values()){
                System.err.println(bean==null?"null":bean.getClass().getName());
            }
            System.err.println("=====ContextRefreshedEvent====="+event.getSource().getClass().getName());
        }else{

        }
    }

}
