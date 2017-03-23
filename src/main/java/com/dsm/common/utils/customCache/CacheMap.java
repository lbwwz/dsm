package com.dsm.common.utils.customCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 缓存器，一个临时的map
 * -装饰类。装饰自 hashMap<K,V>
 * <tt>用来存储短暂对象的缓存类，实现Map接口，内部有一个线程池管理循环的线程用来清除过期（超出设置的过期时间，默认为30s）的对象。
 * 为避免创建过多线程，没有特殊要求请使用getDefault()方法来获取本类的实例。</tt>
 *
 * @param <K>
 * @param <V>
 * @author lbwwz
 */
public class CacheMap<K, V> {

    /**
     * 默认map中entry的的保留时间
     */
    private static final long DEFAULT_TIMEOUT = 3000;

    /**
     * 内部缓存对象的的 map
     */
    private final Map<K, V> map = new HashMap<K, V>();


    /**
     * 对象在缓存器中的驻留时间
     * <tt>表示放入缓存区的对象的声明周期，拆过该驻留时间缓存器将会将该对象从CacheMap对象中移除</tt>
     */
    private long cacheTime;


    /**
     * CacheMap中默认的缓存器对象
     */
    private static CacheMap<Object, Object> defaultInstance;

    /**
     * 控制线程循环的标志参数
     * -控制内部线程的结束和开始，当该参数的值设置为false后，cacheThread中的循环结束，run方法执行完成，线程周期完成
     */
    private boolean cycleFlag;

    /**
     * 获取缓存器中默认的 CacheMap
     * <i>默认设置Key和Value类型均为Object类型</i>
     *
     * @return 默认的缓存器对象 CacheMap<Object, Object>
     */
    public static synchronized CacheMap<Object, Object> getDefaultMap() {
        if (defaultInstance == null) {
            defaultInstance = new CacheMap<Object, Object>();
        }
        return defaultInstance;
    }


    /**
     * 添加要缓存的对象
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        addTimeStamp(System.currentTimeMillis(), key);
        if (map.isEmpty()) {
            new CleanThread().start();
        }
        System.out.print("=>【添加】要缓存的元素：" + key + ":" + value);
        map.put(key, value);
    }


    /**
     * 根据设置的key值获取缓存对象
     *
     * @param key
     * @return
     */
    public V get(K key) {
        return map.get(key);
    }


    /**
     * 记录缓存清理的 清理任务表
     * -非阻塞队列 queue，存放每个任务需要被清理的时间戳时刻 timeStamp 和对应要清理任务的key
     */

    @SuppressWarnings("unchecked")
    private ConcurrentLinkedQueue<CleanTaskEntry<K>> timeStampQueue = new ConcurrentLinkedQueue();


    /**
     * 查看 清理任务表 最前端的元素
     *
     * @return timeStampQueue 队列中的一个任务对象
     */
    private CleanTaskEntry<K> getTimeStamp() {
        return timeStampQueue.peek();
    }

    /**
     * 取出时间戳任务队列最前端的元素
     * <p>timeStampQueue 元素出队列</p>
     * -不同于上面的{@code getTimeStamp}方法，该处取到元素是队列中的元素出队列，{@code  timeStampQueue} 中元素个数会减少一个
     *
     * @return timeStampQueue 队列中的一个任务对象
     */
    private CleanTaskEntry<K> pullTimeStamp() {
        System.out.print("=>任务队列中任务数量：" + timeStampQueue.size());

        /**
         * 如果 timeStampQueue 中的任务执行完毕(队列为空)，则结束循环线程
         */
        if (timeStampQueue.isEmpty()) {
            cycleFlag = false;
            return null;
        }

        return timeStampQueue.poll();
    }


    /**
     * 添加 清理任务单元{@link CleanTaskEntry} 到 timeStampQueue 队列中
     *
     * @param timeStamp 时间戳
     * @param key       对应缓存对象在{@code map}的key
     */
    private void addTimeStamp(long timeStamp, K key) {
        //执行添加操作
        timeStampQueue.add(new CleanTaskEntry<>(timeStamp, key));
    }


    /**
     * 缓存时间为默认{@code DEFAULT_TIMEOUT} 的缓存器
     */
    public CacheMap() {
        this.cacheTime = DEFAULT_TIMEOUT;
//        new ClearThread().start();
    }

    /**
     * 带设置的缓存时间参数的缓存器
     *
     * @param timeout 设置的缓存器的缓存时间 <i>long</i>
     */
    public CacheMap(long timeout) {
        this.cacheTime = timeout;
//        new ClearThread().start();
    }


    /**
     * 缓存器内部的 期缓存清理线程
     * <i>用于删除map中的entry对象是否过期</i>
     * -该线程由缓存器内部的线程池管理
     */
    private class CleanThread extends Thread {
        CleanThread() {
            setName("clear cacheMap thread");
            cycleFlag = true;
            System.out.print("=>线程创建");
        }

        public void run() {
            cleanRun();
        }
    }

    /**
     * 过期缓存回收线程的回收方法
     */
    private void cleanRun() {
        //每隔 cacheTimeout 扫描一次map中的entry对象
        while (cycleFlag) {
            try {
                long now = System.currentTimeMillis();

                synchronized (map) {
                    //时间戳队列取出相应的执行信息
                    CleanTaskEntry<K> cacheEntry = pullTimeStamp();

                    if (cacheEntry != null) {
                        System.out.print("=>睡眠时间:" + Math.abs(cacheEntry.getTime() - now));
                        Thread.sleep(Math.abs(cacheEntry.getTime() - now));

                        map.remove(cacheEntry.getKey());
                        System.out.print("=>【移除】：" + cacheEntry.getKey() + "map对应的元素");
                    }
                }
//                //校验任务队列是否已经全部执行
//                if (map.isEmpty())
//                    cycleFlag = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print("=>线程结束");
    }

//    /**
//     * 管理内部线程运行的线程池
//     */
//    private static ExecutorService threadExecutor;


    /**
     * 内部类 清理任务表 {@code timeStampQueue}中的任务单元
     */
    class CleanTaskEntry<K> {
        /**
         * 任务执行的时间
         */
        long time;

        /**
         * 任务单元对应的缓存map中key
         * -当执行清理任务时，线程通过该key删除{@code map}中保存的对象
         */
        K key;


        CleanTaskEntry(long time, K key) {
            this.time = time + cacheTime;
            this.key = key;
        }

        java.lang.Long getTime() {
            return time;
        }

        void setTime(long time) {
            this.time = time + cacheTime;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

    }
}
