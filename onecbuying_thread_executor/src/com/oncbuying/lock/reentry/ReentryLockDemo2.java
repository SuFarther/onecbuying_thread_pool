package com.oncbuying.lock.reentry;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @ClassName ReentryLockDemo
 * @company 公司
 * @Description 重入锁
 *
 * 重入锁: 传到给下一个方法，重复使用
 * 非重入锁(死锁): 递归使用同步 ReentrantLock重入锁
 * @createTime 2022年08月08日 21:04:04
 */
class  Test2 implements Runnable{

    ReentrantLock lock = new ReentrantLock();

    public  void get() {
       lock.lock();
       try{
           System.out.println("name:"+Thread.currentThread().getName()+",get();");
           set();
       }catch(Exception e){
           e.printStackTrace();
       }finally{
           lock.unlock();
       }
    }

    public  void set() {
        lock.lock();
        try{
            System.out.println("name:"+Thread.currentThread().getName()+",set();");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }
}

public class ReentryLockDemo2 {
    public static void main(String[] args) {
       Test2 ss = new Test2();
       new Thread(ss).start();
       new Thread(ss).start();
       new Thread(ss).start();
       new Thread(ss).start();
    }
}
