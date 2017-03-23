package com.test.other;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/3
 *
 * @author : Lbwwz
 *
 * 定时函数测试类
 */
public class timerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("11232");
            }
        }, 2000,1000);
    }
}
