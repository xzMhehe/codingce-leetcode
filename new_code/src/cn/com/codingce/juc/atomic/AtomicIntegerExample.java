package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    static AtomicInteger value = new AtomicInteger(0);
    static int value1 = 0;
    static int value2 = 0;

    public static void main(String[] args) {
        System.out.println("==============AtomicInteger==============");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(AtomicIntegerExample::atomicIntegerMethod);
        }
        executor.shutdown();
        System.out.println("==============synchronizedMethod==============");
        ExecutorService executor2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor2.execute(AtomicIntegerExample::synchronizedMethod);
        }
        executor2.shutdown();
        System.out.println("==============ordinaryMethod==============");
        ExecutorService executor3 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor3.execute(()->{
                System.out.println(Thread.currentThread().getName() + ", value2: " + value2 + " ");
                value2++;
            });
        }
        executor3.shutdown();
    }

    public static synchronized void atomicIntegerMethod() {
        System.out.println(Thread.currentThread().getName() + ", value: " + value.get() + " ");
        value.incrementAndGet();
    }

    public static synchronized void synchronizedMethod() {
        System.out.println(Thread.currentThread().getName() + ", value1: " + value1 + " ");
        value1++;
    }

}
