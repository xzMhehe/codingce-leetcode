package cn.com.codingce.juc.生产者消费者模型;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者, ReentrantLock方式实现
 * @author mxz
 */
public class ReentrantLockExample {

    private static Integer count = 0;
    private static final Integer maxCount = 10;
    // 创建一个锁对象
    private final Lock lock = new ReentrantLock();
    // 使用 Condition 对象来实现 wait 和 notify 的功能
    // 创建两个条件变量, 一个为缓冲区非满, 一个为缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        ReentrantLockExample example = new ReentrantLockExample();
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(15L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 获取锁
                lock.lock();
                try {
                    while (Objects.equals(count, maxCount)) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产, 目前总共有: " + count + " ***************");
                    // 唤醒消费者
                    notEmpty.signal();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == 0) {
                        try {
                            notEmpty.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费, 目前总共有: " + count + " ---------------");
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
