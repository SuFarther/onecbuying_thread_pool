package com.oncbuying.lock.reentry;

/**
 * @version 1.0
 * @ClassName ReentryLockDemo
 * @company 公司
 * @Description 重入锁
 *
 * 重入锁: 传到给下一个方法，重复使用
 * 非重入锁(死锁): 递归使用同步 synchronized重入锁
 * @createTime 2022年08月08日 21:04:04
 */
class  Test implements Runnable{
    public synchronized void get() {
        System.out.println("name:"+Thread.currentThread().getName()+",get();");
        set();
    }

    public synchronized void set() {
        System.out.println("name:"+Thread.currentThread().getName()+",set();");
    }

    @Override
    public void run() {
        get();
    }
}

public class ReentryLockDemo {
    public static void main(String[] args) {
       Test ss = new Test();
       new Thread(ss).start();
       new Thread(ss).start();
       new Thread(ss).start();
       new Thread(ss).start();
    }
}
