package cn.com.codingce.juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static int ticket = 100;

    public void test() {
        ticket--;
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 公平锁: 锁被释放之后，先申请的线程/进程先得到锁。
         * 非公平锁: 锁被释放之后，后申请的线程/进程可能会先获取到锁，是随机或者按照其他优先级排序的。
         */
        ReentrantLock reentrantLock = new ReentrantLock(true);
        ExecutorService service = Executors.newCachedThreadPool();
        ReentrantLockExample test = new ReentrantLockExample();
        /**
         * 三个窗口，卖票
         * 解决：1、重复票；2、负数票
         */
        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                while (true) {
                    try {
                        Thread.sleep(100L);
                        //reentrantLock.lock();
                        System.out.println(ticket + "=====" + Thread.currentThread().getName());
                        if (ticket > 0) {
                            test.test();
                        } else {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //reentrantLock.unlock();
                    }
                }
            });
        }
        Thread.sleep(4000L);
        System.out.println(ticket);
        service.shutdown();
    }
}
