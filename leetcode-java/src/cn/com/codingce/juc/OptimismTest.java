package cn.com.codingce.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class OptimismTest {
    public static void main(String[] args) {

    }


    // ------------------------- 悲观锁的调用方式 -------------------------
    // synchronized
    public synchronized void testMethod() {
        // 操作同步资源
    }

    // ReentrantLock
    private ReentrantLock lock = new ReentrantLock(); // 需要保证多个线程使用的是同一个锁

    public void modifyPublicResources() {
        lock.lock();
        try {

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            // 操作同步资源
            lock.unlock();
        }

    }



    // ------------------------- 乐观锁的调用方式 -------------------------
    private AtomicInteger atomicInteger = new AtomicInteger();  // 需要保证多个线程使用的是同一个AtomicInteger
    public void test() {
        atomicInteger.incrementAndGet(); //执行自增1
    }
}
