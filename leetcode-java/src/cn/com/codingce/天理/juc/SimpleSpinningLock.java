package cn.com.codingce.天理.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleSpinningLock {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        SimpleSpinningLock simpleSpinningLock = new SimpleSpinningLock();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    simpleSpinningLock.lock();
                    ++count;
                    simpleSpinningLock.unLock();
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();
        // 多次执行输出均为：100 ，实现了锁的基本功能
        System.out.println(count);
    }

    /**
     * 持有锁的线程，null表示锁未被线程持有
     */
    private AtomicReference<Thread> ref = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!ref.compareAndSet(null, currentThread)) {
            //当ref为null的时候compareAndSet返回true，反之为false
            //通过循环不断的自旋判断锁是否被其他线程持有
        }
    }

    public void unLock() {
        Thread cur = Thread.currentThread();
        if (ref.get() != cur) {
            //exception ...
        }
        ref.set(null);
    }
}
