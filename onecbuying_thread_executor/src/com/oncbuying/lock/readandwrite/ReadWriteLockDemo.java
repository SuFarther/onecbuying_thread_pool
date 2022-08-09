package com.oncbuying.lock.readandwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName ReadWriteLockDemo
 * @company 公司
 * @Description TODO
 * @createTime 2022年08月09日 06:23:23
 */


public class ReadWriteLockDemo {
    static private volatile Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl =  new ReentrantReadWriteLock();
    //读
    static Lock r = rwl.readLock();
    //写
    static Lock w = rwl.writeLock();

    /*
    **
     * @description: 写操作
     * @param: key
    * @param: value
     * @return: java.lang.Object
     * @author 苏东坡
     * @date: 2022/8/9 7:18 AM
     */
    static public Object put(String key, Object value) {
        w.lock();
        try {
            System.out.println("正在写入 key:" + key + ",value:" + value + "开始.....");
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
            Object obj = map.put(key, value);
            System.out.println("正在写入 key:" + key + ",value:" + value + "结束.....");
            System.out.println();
            return obj;
        }finally{
            w.unlock();
        }
        return value;
    }

    /*
    **
     * @description: 读操作
     * @param: key
     * @return: void
     * @author 苏东坡
     * @date: 2022/8/9 7:18 AM
     */
    static public void get(String key) {
       r.lock();
        try {
            System.out.println("正在读取 key:" + key + "开始");
            Thread.sleep(1000);
        }catch(Exception e) {
            e.printStackTrace();
            Object value = map.get(key);
            System.out.println("正在读取 key:" + key + ",value:" + value + "结束.....");
        }finally{
            r.unlock();
        }
    }

    /*
    **
     * @description:
     * @param: args 清空所有内容
     * @return: void
     * @author 苏东坡
     * @date: 2022/8/9 7:23 AM
     */
    static void clear(){
        w.lock();
        try {
            map.clear();
        }finally{
            w.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ReadWriteLockDemo.put(i + "", i + "");
                }

            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ReadWriteLockDemo.get(i + "");
                }

            }
        }).start();
    }
}
