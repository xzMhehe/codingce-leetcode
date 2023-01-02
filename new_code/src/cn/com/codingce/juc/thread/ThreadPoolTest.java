package cn.com.codingce.juc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            executor.submit(() -> {
//                System.out.println("Thread id is " + Thread.currentThread().getId());
//                try {
//                    sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
        //createSingleThreadPool();
        //createFixedThreadPool();
        //createCachedThreadPool();
        //createScheduledThreadPool();
        //createNewWorkStealingPool();
        //createThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Long> future = executor.submit(() -> {
            System.out.println("task is executed");
            return System.currentTimeMillis();
        });
        System.out.println("task execute time is: " + future.get());
    }

    private static void createThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5, true), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void createNewWorkStealingPool() {
        ExecutorService forkJoin = Executors.newWorkStealingPool();
        forkJoin.execute(() -> {
            System.out.println("i====>" + 1 + " " + Thread.currentThread().getId());

        });
        forkJoin.execute(() -> {
            System.out.println("i====>" + 2 + " " + Thread.currentThread().getId());

        });
        forkJoin.execute(() -> {
            System.out.println("i====>" + 3 + " " + Thread.currentThread().getId());

        });
        forkJoin.execute(() -> {
            System.out.println("i====>" + 4 + " " + Thread.currentThread().getId());

        });
        forkJoin.execute(() -> {
            System.out.println("i====>" + 5 + " " + Thread.currentThread().getId());
        });
    }

    private static void createScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        System.out.println(new Date() + " 提交任务");
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.schedule(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

    private static void createCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void createSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void createFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
