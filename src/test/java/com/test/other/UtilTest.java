package com.test.other;

import com.dsm.common.utils.EncryptUtils;
import com.dsm.common.utils.StringHandleUtils;
import com.dsm.common.utils.chineseSplit.dic.Dictionary;
import com.dsm.common.utils.configContext.ConfigContext;
import com.dsm.common.utils.configContext.ConfigContextFactory;
import com.dsm.model.user.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Lbwwz on 2016/8/8.
 */
public class UtilTest {

    @Test
    public void test1(){
        String a = "20160708.jpg";
        System.out.println(StringHandleUtils.getFileExt(a));
    }

    @Test
    public void test2() throws IOException {
        for(;;){
            ConfigContext configContext = ConfigContextFactory.CreateContext("test.properties");
            System.out.println(configContext);
            System.out.println(configContext.getConfigVal("asd"));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

    @Test
    public void test3(){
        long newDateModifiedInJava = new File("src/test/resources/test.properties").lastModified();
        System.out.println(newDateModifiedInJava);
    }

    @Test
    public void test4(){
        String a = "aSD,SSc,JPG";

        List<String> s = Arrays.asList(a.toLowerCase().split(","));

        System.out.println(s);
    }


    @Test
    public void test5(){
        String s = "/adshua/adas";
        System.out.println(s.substring(1));

    }

    @Test
    public void test6(){
        long dataTime = new File("E:\\ideaWorkspace\\ideaWorkspace\\dsm\\src\\main\\java\\com\\dsm\\service/").lastModified();
        System.out.println(dataTime);
    }



//    public static void main(String args[]) throws InterruptedException {
//        new Thread(()->{
//            for(int i = 0;;i++){
//                System.out.println();
//                System.out.print(i+":");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
////        CachePool<String,String> pool = CacheExecutor.newCachePool();
//        CacheMap<String, String> map = new CacheMap<String, String>(3000);
//
//
//        map.put("123","12345");     //  开始添加一个元素
//        Thread.sleep(2000);
//        map.put("432","1231231");     //第二秒后添加一个元素
//        Thread.sleep(2500);
//        System.out.print("=>"+map.get("123"));
//        System.out.print("=>"+map.get("432"));
//        map.put("345","qweqe1");    //4秒半添加一个元素
//
//        Thread.sleep(3500);         //第八秒添加一个元素
//        map.put("111111","121211111111");
//    }


    @Test
    public void test8(){
        List<Long> testList = new ArrayList<>();

        testList.add(3L);
        testList.add(2L);
        testList.add(8L);
        Collections.sort(testList);
        System.out.println(testList);

    }


    /**
     * 队列的测试 非阻塞队列 ConcurrentLinkedQueue 测试
     */
    @Test
    public void tes9(){
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();

        queue.add("1");
        queue.add("2");
        queue.add("3");

        System.out.println( queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println( queue.poll());
        System.out.println( queue.poll());
        System.out.println( queue.poll());
    }


    @Test
    public void test10() throws InterruptedException {

        for (int i = 0; i<100; i++){
            System.out.println((int)(Math.random()*10+90));
            Thread.sleep(10);
        }

    }

    @Test
    public void test11(){
        String str = "1234512312341234";
        String str2 = "12345123123412a4";
        String str3 = "123451231234aa234";
        System.out.println(isPSWD(str));
        System.out.println(isPSWD(str2));
        System.out.println(isPSWD(str3));
    }

    public static boolean isPSWD(String tel) {

        final String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return tel.matches(regex);
    }

    @Test
    public void test12(){
//        String s = UtilTest.class.getClassLoader().getResource("").getPath();
//        System.out.println(s);

        System.out.println(new File("/ajsida/asda/jais.jsp").lastModified());
    }

    @Test
    public void test13(){
        String password = "123456";
        String enc = EncryptUtils.encryptMD5(password);

        System.out.println(enc);
    }

    @Test
    public void test14(){
        User user1 = new User();
        user1.setHeadImage("1");
        user1.setMobile("123");
        User user2 = new User();
        user2.setMobile("123456");
        user2.setHeadImage("12332112221112");
//        user2.setDefaultAddress(1);
        System.out.println(user1);
        System.out.println(user2);
        BeanUtils.copyProperties(user1,user2);
        System.out.println(user1);
        System.out.println(user2);
    }

    //测试中文分词
    @Test
    public void test15(){
        String sentence = "牛仔上衣折扣";
        List<String> words = StringHandleUtils.cutWords(sentence);
        System.out.println(words);

    }

    public static void main(String[] args) {
        ClassLoader load = Dictionary.class.getClassLoader();
        InputStream is = load.getResourceAsStream("redis.properties");
        if(is == null){
            System.out.println("kong");
        }
        System.out.println(is);
    }


}
