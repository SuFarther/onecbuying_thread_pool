package com.oncbuying.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName NewSingleThreadExecutorDemo
 * @company 公司
 * @Description  结果依次输出，相当于顺序执行各个任务
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 */
public class NewSingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {

            final int  temp = i;
            newSingleThreadExecutor.execute(()-> System.out.println("threadName:"+Thread.currentThread().getName() + ",i:"+temp));
        }
    }
}
