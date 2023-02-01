package cn.com.codingce.juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {

//    // 创建读写锁
//    final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    // 获得读锁
//    final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
//    // 获得写锁
//    final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        //readAndRead();

        //readAndWrite();

        writeAndWrite();
    }

    /**
     * 读读不互斥
     * <p>
     * 多个线程可以同时获取到读锁, 称之为读读不互斥.
     */
    public static void readAndRead() {

        // 创建读写锁
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 创建读锁
        final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        ExecutorService service = Executors.newCachedThreadPool();

        // 使用读锁
        service.execute(() -> {
            readLock.lock();
            try {
                System.out.println(" t1 得到读锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" t1 释放读锁");
                readLock.unlock();
            }
        });

        // 使用读锁
        service.execute(() -> {
            readLock.lock();
            try {
                System.out.println(" t2 得到读锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" t2 释放读锁");
                readLock.unlock();
            }
        });

        service.shutdown();
    }

    /**
     * 读写互斥
     * <p>
     * 读锁和写锁同时使用是互斥的（也就是不能同时获得）, 这称之为读写互斥.
     */
    public static void readAndWrite() {

        // 创建读写锁
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 创建读锁
        final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        // 创建写锁
        final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        ExecutorService service = Executors.newCachedThreadPool();

        // 使用读锁
        service.execute(() -> {
            readLock.lock();
            try {
                System.out.println(" t1 得到读锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" t1 释放读锁");
                readLock.unlock();
            }
        });

        // 使用写锁
        service.execute(() -> {
            writeLock.lock();
            try {
                System.out.println(" t2 得到写锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" t2 释放写锁");
                writeLock.unlock();
            }
        });

        service.shutdown();
    }

    public static void writeAndWrite() {

        // 创建读写锁
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 创建写锁
        final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        ExecutorService service = Executors.newCachedThreadPool();

        // 使用写锁
        service.execute(() -> {
            writeLock.lock();
            try {
                System.out.println(" t1 得到写锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" t1 释放写锁");
                writeLock.unlock();
            }
        });

        // 使用写锁
        service.execute(() -> {
            writeLock.lock();
            try {
                System.out.println(" t2 得到写锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" t2 释放写锁");
                writeLock.unlock();
            }
        });

        service.shutdown();
    }

}
