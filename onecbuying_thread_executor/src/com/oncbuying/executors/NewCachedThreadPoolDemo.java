package com.oncbuying.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName NewCachedThreadPoolDemo
 * @company 公司
 * @Description 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
 * @createTime 2022年08月07日 15:46:46
 */
public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        //无限大小线程池 jvm自动回收
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final  int temp = i;
//            newCachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println(Thread.currentThread().getName() + temp);
//                }
//            });
            newCachedThreadPool.execute(()-> System.out.println("threadName:"+Thread.currentThread().getName() + ",i:"+temp));
        }
        newCachedThreadPool.shutdown();
    }
}
