package com.test.springTest;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/7/31
 *
 * @author : Lbwwz
 */
public class SpringListenerTest {

    public static void main(String[] args) {
        //构建广播器
        ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        //添加开始监听器
        multicaster.addApplicationListener(new MyStartListener());
        //添加结束监听器
        multicaster.addApplicationListener(new MyEndListener());

        System.out.println("--1---");
        multicaster.multicastEvent(new MyStartEvent(""));//广播开始事件
        System.out.println("--2---");
        multicaster.multicastEvent(new MyEndEvent(""));//广播结束事件
        //移除监听者
//        multicaster.removeApplicationListener();
    }

    public static class MyStartEvent extends ApplicationEvent {

        public MyStartEvent(Object source) {
            super(source);
        }
    }

    public static class MyEndEvent extends ApplicationEvent {
        public MyEndEvent(Object source) {
            super(source);
        }
    }

    /**
     * 继承监听器
     */
    public static class MyStartListener implements ApplicationListener {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            //监听开始事件
            if (event instanceof MyStartEvent ) {
                System.out.println("程序开始运行Listener！");
            }
        }
    }

    /**
     * 继承监听器
     */
    public static class MyEndListener implements ApplicationListener {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            //监听结束事件
            if (event instanceof MyEndEvent) {
                System.out.println("程序结束运行...Listener");
            }
        }
    }
}
