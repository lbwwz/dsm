package com.dsm.common.utils.customCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lbw on 2016/8/17.
 *
 * 时间戳记录表
 * -一个顺序的任务结构，按照内部元素(任务单元){@link TimeStampPiping.CleanTaskEntry}的 time（时间戳）属性从大到小排序
 * <p>添加元素后表结构依然保持表的顺序结构，元素总是从排序最后的元素开始被取出（时间戳最小）</p>
 */
public class TimeStampPiping<K> {

    private int taskNum;

    private List<CleanTaskEntry<K>> tackPiping;


    /**
     * 动态初始化任务表中的参数
     */ {
        /**
         * 初始化内部的存储结构，使用一个线程安全的集合结构
         */
        tackPiping = new ArrayList<>();
        Collections.synchronizedList(tackPiping);

        //初始化任务表中任务的个数
        taskNum = 0;
    }

    TimeStampPiping() {
    }

    /**
     * 获取任务表中当前任务数量
     *
     * @return int
     */
    int getTaskNum() {
        return taskNum;
    }

    /**
     * 检查任务表中任务是否为空
     *
     * @return 是否为空的判定
     */
    boolean isEmpty() {
        return taskNum == 0;
    }


    /**
     * 想任务表中添加任务
     *
     * @param timeStamp 任务的执行的时间戳
     * @param key       任务时间戳对应的key
     */
    synchronized void addTimeStamp(long timeStamp, K key) {

        CleanTaskEntry<K> task = new CleanTaskEntry<>(timeStamp, key);
        sort(task);
        taskNum++;
    }

    /**
     * 优化的顺序链表的插入排序方法
     *
     * @param task 要插入的任务
     */
    private void sort(CleanTaskEntry<K> task) {
        if (taskNum == 0) {
            tackPiping.add(task);
            return;
        }

        int start = 0;
        int end = taskNum - 1;
        int index = (end + start) >> 1;

        for (int temp = -1; temp != index; temp = index, index = (end + start) >> 1) {
            if (task.compareTo(tackPiping.get(index)) > 0)
                end = index;
            else
                start = index + 1;
        }

        //根据任务的执行时间戳顺序判定插入的位置
        if (task.compareTo(tackPiping.get(index)) > 0)
            tackPiping.add(index, task);
        else
            tackPiping.add(index + 1, task);
    }


    /**
     * 取出任务表中的任务
     * -执行该方法，每次取出表中任务时间戳最小的任务，任务表中的该任务被取出后便会从任务表中移除
     *
     * @return {@link TimeStampPiping.CleanTaskEntry}
     */
    CleanTaskEntry<K> pullTimeStamp() {
        CleanTaskEntry<K> outTask = tackPiping.get(taskNum - 1);
        tackPiping.remove(taskNum - 1);
        taskNum--;
        return outTask;
    }

    /**
     * 获取任务表中的任务
     * -<i>查询方法</i> 每次获取表中任务时间戳最小的任务，不同于{@code pullTimeStamp},任务表中的任务数量并不会减少
     *
     * @return {@link TimeStampPiping.CleanTaskEntry}
     */
//    CleanTaskEntry<K> getNextTimeStamp() {
//        return tackPiping.get(taskNum - 1);
//    }


    /**
     * {@link TimeStampPiping}内部类 中的任务单元
     * @param <T> 任务中存放的 key 类型
     */
    class CleanTaskEntry<T> implements Comparable<CleanTaskEntry<T>> {
        /**
         * 任务执行的时间
         */
        long time;

        /**
         * 任务单元对应的缓存map中key
         * -当执行清理任务时，线程通过该key删除{@code map}中保存的对象
         */
        T key;

        CleanTaskEntry(long time, T key) {
            this.time = time;
            this.key = key;
        }

        long getTime() {
            return time;
        }

        void setTime(long time) {
            this.time = time;
        }

        T getKey() {
            return key;
        }

        void setKey(T key) {
            this.key = key;
        }

        @Override
        public int compareTo(CleanTaskEntry<T> o) {
            return (int) (this.time - o.getTime());
        }
    }
}
