package com.dsm.common.utils.customCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lbw on 2016/8/17.
 *
 * 缓存池的具体实现类
 */
public class CachePool<K, V> {

    /**
     * 内部缓存对象的的 map
     */
    private final Map<K, V> cacheBase = new HashMap<>();


    /**
     * 控制线程循环的标志参数
     * -控制内部线程的结束和开始，当该参数的值设置为false后，cacheThread中的循环结束，run方法执行完成，线程周期完成
     */
    private boolean cycleFlag;

    /**
     * 时间戳，标志{@code timeStampPiping}-任务表中pull出的任务的执行时间
     */
    private long nextTaskTimeStamp;


    /**
     * 记录 缓存清理任务的 清理任务表
     * -{@link TimeStampPiping}，存放每个任务需要被清理的时间戳时刻 timeStamp 和对应要清理任务的key
     */
    private final TimeStampPiping<K> timeStampPiping = new TimeStampPiping<>();

    /**
     * 构造器
     */
    private CachePool() {}

    private static class SingletonHolder {
        private final static CachePool instance = new CachePool();
    }


    /**
     * 获取单例的 CachePool 对象
     *
     * @return 单例对象-{@link CachePool}
     */
    public static CachePool getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 添加要缓存的对象
     * <p>添加方法与移除的方法同步</p>
     *
     * @param key       对应缓存对象的键值
     * @param value     缓存的信息
     * @param delayTime 缓存的时间 不能为负数；
     * @return 缓存对象是否添加成功
     */
    public boolean put(K key, V value, long delayTime) {
        if(delayTime<0){
            throw new IllegalArgumentException("缓存的时间不能为负数！");
        }

        // 校验添加的key值是否存在，存在返回添加失败-false
        if (cacheBase.get(key) == null) {
            synchronized (this) {
                long newTimeStamp = System.currentTimeMillis()+delayTime;

                /**
                 * 新的清理任务缓存时间小于 main清理线程池 中线程睡眠时间
                 * -这时候在{@link CacheExecutor.looseCleanExecutor}中开辟单次线程任务
                 * <p>此时主清理线程因为下次清理时间未到来，线程处在休眠状态，而新任务又要在主清理线程下次执行前执行，故此时将
                 * 清理任务放入<i>散列任务清理线程池中，使用单独的线程执行该次任务，任务执行完成之后，线程将直接被回收</i></p>
                 */
                if (newTimeStamp <= nextTaskTimeStamp) {

                    //单次缓存声明周期管理任务添加到loose线程池中
                    CacheExecutor.getLooseCleanExecutor().execute(() -> cleanOperate(delayTime, key));
                } else {

                    addTimeStamp(newTimeStamp, key);
                    /**
                     * 这里要注意，mainCleanExecutor线程池中新任务的创建不是根据{@link CachePool}的创建而创建的，该线程池中
                     */
                    if (cacheBase.isEmpty()) {
                        /**
                         * 将清理线程要执行的操作交给{@link CacheExecutor}中的 mainCleanExecutor管理
                         */
                        CacheExecutor.getMainCleanExecutor().execute(new CleanThread());
                    }
                }
                cacheBase.put(key, value);
                return true;
            }
        }
        return false;
    }


    /**
     * 根据设置的key值获取缓存对象
     *
     * @param key 放入 CachePool 中缓存对象对应的键值
     * @return 根据简直查找，返回 CachePool 中保存的对象，若对象不存在则返回 null
     */
    public V get(K key) {
        return cacheBase.get(key);
    }

    /**
     * 根据键值key移除 缓存区cacheBase 的缓存对象
     *
     * @param key 要删除元素的对应键值
     */
    public void remove(K key) {

        cacheBase.remove(key);
    }

    /**
     * 向 清理任务表 中添加新的任务
     *
     * @param timeStamp 任务执行的时间戳
     * @param key       键，任务对应 {@code cacheBase}中的key
     */
    private void addTimeStamp(long timeStamp, K key) {
        /**
         * 这里的添加方法与pull方法做互斥处理，防止线程安全的问题
         */
        synchronized (cacheBase) {
            timeStampPiping.addTimeStamp(timeStamp, key);
        }
    }

    /**
     * 取出 <i>清理任务表</i> 下一个要执行的任务,表中任务数减少一个
     * <P>若任务表中的元素为空，表示任务已经全部被执行完毕，这时候设置{@link CacheExecutor}中的mainCleanExecutor管理的线程任务结束</P>
     * -不同于上面的{@code getTimeStamp()}方法，该处取到元素是队列中的元素出队列，{@code  timeStampQueue} 中元素个数会减少一个
     *
     * @return 清理任务表 中的下一个任务对象{@link TimeStampPiping.CleanTaskEntry}
     */
    private TimeStampPiping<K>.CleanTaskEntry<K> pullTimeStamp() {

        synchronized (cacheBase) {
            /**
             * 如果 timeStampQueue 中的任务执行完毕(队列为空)，则结束循环线程
             */
            if (timeStampPiping.getTaskNum() == 0) {
                /**
                 * 任务表中需要清理的任务为空，关闭{@link CacheExecutor.mainCleanExecutor}线程池中的线程
                 */
                cycleFlag = false;
                return null;
            }
            return timeStampPiping.pullTimeStamp();
        }
    }


    /**
     * 缓存器内部的 期缓存清理线程
     * <i>用于删除map中的entry对象是否过期</i>
     * -该线程由{@link CacheExecutor} 中的 mainCleanExecutor线程池管理
     */
    private class CleanThread extends Thread {
        CleanThread() {
            /**
             * 开启线程的同时启动线程中的循环
             */
            cycleFlag = true;
        }

        @Override
        public void run() {

            while (cycleFlag) {
                try {
                    /**
                     * 从{@code TimeStampPiping}任务队列 取出相应的任务信息，
                     */
                    TimeStampPiping<K>.CleanTaskEntry<K> cacheEntry = pullTimeStamp();

                    /**
                     * 任务是否为空，若为空则表示该清理线程阶段性清理工作已经完成，这时候跳出循环
                     * 这时候释放该线程到{@link CacheExecutor.mainCleanExecutor}线程池中
                     */
                    if (cacheEntry != null) {
                        nextTaskTimeStamp = cacheEntry.getTime();
                        cleanOperate(computeSleepTime(nextTaskTimeStamp), cacheEntry.getKey());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private long computeSleepTime(long taskTimeStamp) {
        long tempTime = taskTimeStamp - System.currentTimeMillis();
        return (tempTime < 0) ? 0 : tempTime;
    }

    /**
     * 两种清理线程池中的要执行的清理方法
     *
     * @param delayTime 清理的延迟时间<i>针对当前时间而言</i>
     * @param key       对应 cacheBase中缓存对象的 key值
     */
    private void cleanOperate(long delayTime, K key) {
        try {
            Thread.sleep(delayTime);

            /**
             * 删除 cacheBase中的元素
             * <p>这里<i>删除map中的元素</i>用到了同步方法，与{@code put}方法做同步操作</p>
             * <code></code>
             */
            synchronized (this) {
                cacheBase.remove(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
