package cn.com.codingce.juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {

    /**
     * 同步一个代码块，它只作用于同 一个对象，如果调用 两个对象 上的同步代码块，就不会进行同步。
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 同步一个方法，和同步代码块一样，作用于同一个对象，如果调用 两个对象 上的同步代码块，就不会进行同步。
     */
    public synchronized void func2() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步一个类，作用于整个类，也就是说两个线程调用 同一个类 的 相同｜不同 对象上 的这种同步语句，也会进行同步。
     */
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 同步一个静态方法
     */
    public synchronized static void func4() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        SynchronizedExample example1 = new SynchronizedExample();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(example1::func3);
//        executorService.execute(example1::func3);

//        SynchronizedExample example1 = new SynchronizedExample();
//        SynchronizedExample example2 = new SynchronizedExample();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(() -> example2.func3());
//        executorService.execute(() -> example1.func3());
//        executorService.shutdown();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SynchronizedExample::func4);
        executorService.execute(SynchronizedExample::func4);
    }
}
