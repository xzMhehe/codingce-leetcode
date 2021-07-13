package cn.com.codingce.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author williamma
 */
public class MyThreadPoolExecutor {
    private static ExecutorService pool = null;

    public static void main(String[] args) {
        pool = new ThreadPoolExecutor(1,
                2,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            pool.execute(new ThreadTask());
        }
    }

    static class ThreadTask implements Runnable {

        public ThreadTask() {

        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

