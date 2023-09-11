package cn.com.codingce.juc.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 线程池工具类
 */
public class ThreadPoolUtils {

    /**
     * 线程池
     */
    private final ThreadPoolExecutor executor;

    /**
     * 线程工厂
     */
    private CustomThreadFactory threadFactory;

    /**
     * 异步执行结果
     */
    private final List<CompletableFuture<Void>> completableFutures;

    /**
     * 拒绝策略
     */
    private final CustomAbortPolicy abortPolicy;

    /**
     * 失败数量
     */
    private final AtomicInteger failedCount;

    public ThreadPoolUtils(int corePoolSize, int maximumPoolSize, int queueSize, String poolName) {
        this.failedCount = new AtomicInteger(0);
        this.abortPolicy = new CustomAbortPolicy();
        this.completableFutures = new ArrayList<>();
        this.threadFactory = new CustomThreadFactory(poolName);
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize), this.threadFactory, abortPolicy);
    }

    /**
     * 执行任务
     */
    public void execute(Runnable runnable) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(runnable, executor);
        // 设置好异常情况
        future.exceptionally(e -> {
            failedCount.incrementAndGet();
            System.out.println("Task Failed..." + e);
            e.printStackTrace();
            return null;
        });
        // 任务结果列表
        completableFutures.add(future);
    }

    /**
     * 执行自定义runnable接口（可省略，只是加了个获取taskName）
     */
    public void execute(SimpleTask runnable) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(runnable, executor);
        // 设置好异常情况
        future.exceptionally(e -> {
            failedCount.incrementAndGet();
            System.out.println("Task [" + runnable.taskName + "] Failed..." + e);
            e.printStackTrace();
            return null;
        });
        // 任务结果列表
        completableFutures.add(future);
    }

    /**
     * 停止线程池
     */
    public void shutdown() {
        executor.shutdown();
        System.out.println("************************停止线程池************************");
        System.out.println("** 活动线程数：" + executor.getActiveCount() + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("** 等待任务数：" + executor.getQueue().size() + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("** 完成任务数：" + executor.getCompletedTaskCount() + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("** 全部任务数：" + executor.getTaskCount() + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("** 拒绝任务数：" + abortPolicy.getRejectCount() + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("** 成功任务数：" + (executor.getCompletedTaskCount() - failedCount.get()) + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("** 异常任务数：" + failedCount.get() + "\t\t\t\t\t\t\t\t\t\t**");
        System.out.println("**********************************************************");
    }

    /**
     * 获取任务执行情况
     * 之所以遍历taskCount数的CompletableFuture，是因为如果有拒绝的任务，相应的CompletableFuture也会放进列表，而这种CompletableFuture调用get方法，是会永远阻塞的。
     */
    public boolean getExecuteResult() {
        // 任务数，不包含拒绝的任务
        long taskCount = executor.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            CompletableFuture<Void> future = completableFutures.get(i);
            try {
                // 获取结果，这个是同步的，目的是获取真实的任务完成情况
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("java.util.concurrent.CompletableFuture.get() Failed ..." + e);
                return false;
            }
            // 出现异常，false
            if (future.isCompletedExceptionally()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 线程工厂
     */
    private static class CustomThreadFactory implements ThreadFactory {

        private final String poolName;
        private final AtomicInteger count;

        private CustomThreadFactory(String poolName) {
            this.poolName = poolName;
            this.count = new AtomicInteger(0);
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            // 线程名，利于排查
            thread.setName(poolName + "-[线程" + count.incrementAndGet() + "]");
            return thread;
        }
    }

    /**
     * 自定义拒绝策略
     */
    private static class CustomAbortPolicy implements RejectedExecutionHandler {
        /**
         * 拒绝的任务数
         */
        private final AtomicInteger rejectCount;

        private CustomAbortPolicy() {
            this.rejectCount = new AtomicInteger(0);
        }

        private AtomicInteger getRejectCount() {
            return rejectCount;
        }

        /**
         * 这个方法，如果不抛异常，则执行此任务的线程会一直阻塞
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("Task " + r.toString() + " rejected from " + e.toString() + " 累计：" + rejectCount.incrementAndGet());
        }
    }

    /**
     * 只是加了个taskName，可自行实现更加复杂的逻辑
     */
    public abstract static class SimpleTask implements Runnable {
        /**
         * 任务名称
         */
        private String taskName;

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }

}
