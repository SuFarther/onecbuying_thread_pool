package com.oncbuying.lock.optional;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName OptionalLock
 * @company 公司
 * @Description 自旋锁
 * 当一个线程 调用这个不可重入的自旋锁去加锁的时候没问题，当再次调用lock()的时候，因为自旋锁的持有引用已经不为空了，该线程对象会误认为是别人的线程持有了自旋锁
 * 使用了CAS原子操作，lock函数将owner设置为当前线程，并且预测原来的值为空。unlock函数将owner设置为null，并且预测值为当前线程。
 * 当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，直至第一个线程调用unlock函数将owner设置为null，第二个线程才能进入临界区。
 *
 * 程序运行会卡,千万别动,自选锁只是将当前线程不停地执行循环体,不进行线程状态的改变,所以响应速度更快。但当线程数不停增加时，性能下降明显，因为每个线程都需要执行 ，占用CPU时间。
 * 如果线程竞争不激烈，并且保持锁的时间段。适合使用自选锁
 * @createTime 2022年08月09日 16:10:10
 */
class SpinLock{
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while(!sign.compareAndSet(null,current)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current,null);
    }
}


public class OptionalLock implements Runnable{
    static int sum;
    private  SpinLock  lock;

    public  OptionalLock(SpinLock lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        for (int i = 0; i < 100; i++) {
            OptionalLock ol = new OptionalLock(lock);
            Thread t = new Thread(ol);
            t.start();
        }
        Thread.sleep(1000);
        System.out.println(sum);
    }

    @Override
    public void run() {
        this.lock.lock();
        this.lock.lock();
        sum++;
        this.lock.unlock();
        this.lock.unlock();
    }
}
