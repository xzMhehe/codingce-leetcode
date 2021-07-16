package cn.com.codingce.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无锁编程 CAS demo
 */
public class MyCASDemo {

    static AtomicInteger num = new AtomicInteger(0);

    static int numOne = 0;


    public static void main(String[] args) {


        // 未同步 多条语句打印了相同的值 说明线程间未进行正确通信
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (numOne < 1000) {
                        System.out.println("thread name: " + Thread.currentThread().getName() + ": " + numOne++);
                    }
                }
            });

            thread.start();
        }

        // CAS AtomicInteger demo
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (num.get() < 1000) {
                        System.out.println("thread name: " + Thread.currentThread().getName() + ": " + num.incrementAndGet());
                    }
                }
            });

            thread.start();
        }
        // 互斥锁（悲观锁）
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (MyCASDemo.class) {
                        while (numOne < 1000) {
                            System.out.println("thread name: " + Thread.currentThread().getName() + ": " + numOne++);
                        }
                    }
                }
            });

            thread.start();
        }

    }


}
