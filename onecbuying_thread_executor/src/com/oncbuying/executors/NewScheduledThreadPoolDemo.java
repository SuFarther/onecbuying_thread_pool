package com.oncbuying.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName NewScheduledThreadPoolDemo
 * @company 公司
 * @Description 创建一个定长线程池，支持定时及周期性任务执行创建一个定长线程池，支持定时及周期性任务执行
 * @createTime 2022年08月07日 21:01:01
 */
public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            newScheduledThreadPool.schedule(()-> System.out.println("threadName:"+Thread.currentThread().getName() + ",i:"+temp),3,TimeUnit.SECONDS);
        }
        newScheduledThreadPool.shutdown();
    }
}
