package com.oncbuying.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName CASNoLockDemo
 * @company 公司
 * @Description CAS无锁机制
 * 判断V更新变量值,E期望值是否一致，如果一致,才会将N的值替换成V,如果V的值和E的值不一致,说明有其他线程在做了更新了，
 * 则当前线程什么都不要做。最后,CAS返回当前V的真实值
 * @createTime 2022年08月09日 15:56:56
 */
public class CASNoLockDemo {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        ai.incrementAndGet();
    }
}
