package com.oncbuying.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName NewFixedThreadPoolDemo
 * @company 公司
 * @Description 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 总结:因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
 * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
 * @createTime 2022年08月07日 21:09:09
 */
public class NewFixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int  temp = i;
            newFixedThreadPool.execute(()-> System.out.println("threadName:"+Thread.currentThread().getName() + ",i:"+temp));
        }
        newFixedThreadPool.shutdown();
    }
}
