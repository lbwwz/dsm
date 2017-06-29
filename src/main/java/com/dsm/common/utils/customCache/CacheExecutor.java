package com.dsm.common.utils.customCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lbw on 2016/8/17.
 *
 */
public class CacheExecutor {


    private  static volatile CachePool cachePool;

    private CacheExecutor(){

    }

    /**
     * 静态的工厂方法
     * 创建缓存池
     * @return CachePool缓存池对象
     */
    public static CachePool newCachePool(){

        if (cachePool == null){

            synchronized (CacheExecutor.class){
                if(cachePool==null){
                    /**
                     * 初始化散状清理线程池，使用CachedThreadPool，动态线程池
                     */
                    looseCleanExecutor = Executors.newCachedThreadPool();

                    /**
                     * 初始化任务表清理线程池，该线程用于执行{@link CachePool}中 清理任务表 中的清理任务
                     * 负责清理
                     */
                    mainCleanExecutor = Executors.newCachedThreadPool();

                    //创建缓存池
                    cachePool = CachePool.getInstance();
                }
            }
        }


        //创建线程池对象并返回
        return cachePool;
    }

    /**
     * 散状缓存任务清理线程池
     * -管理池中 清理缓存 的线程池
     * <p>对于{@link CachePool}中的任务队列，当新添加的缓存对象缓存世时间小于 CachePool中任务表<i>timeStampPiping</i>
     * 最近一次的任务延时时间，由于内部的清理任务表清理线程休眠，这事调用该线程中的线程对该特定的缓存对象执行延迟清理操作</p>
     */
    private static ExecutorService looseCleanExecutor;

    /**
     * 静态方法，获取<i>散状缓存任务清理线程池</i>
     * -仅提供给该包中的内部对象{@link CachePool}调用
     *
     * @return 缓存池中管理散状任务的线程池对象，是一个线程数动态化增减的线程池
     */
    static ExecutorService getLooseCleanExecutor() {
        return looseCleanExecutor;
    }

    /**
     * main清理线程线程池
     * 用户清除{@link CachePool}中的添加的临时缓存
     * -该线程池中仅维持一个用于执行{@code timeStampPiping}清理任务的清理线程
     */
    private static ExecutorService mainCleanExecutor;

    /**
     * 静态方法，获取<i>main任务清理线程池</i>
     * -仅提供给该包中的内部对象{@link CachePool}调用
     *
     * @return 缓存池中管理 {@link TimeStampPiping.CleanTaskEntry}缓存任务执行的主清理线程池对象
     */
    static ExecutorService getMainCleanExecutor() {
        return mainCleanExecutor;
    }
}
