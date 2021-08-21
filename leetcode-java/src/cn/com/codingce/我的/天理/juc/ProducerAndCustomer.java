package cn.com.codingce.我的.天理.juc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 怎么实现实现一个生产者消费者？
 *
 *
 * 使用queue作为一个队列，存放数据，并且使用Synchronized同步锁，每次只能同时存在一个线程来生产或者消费数据，
 *
 * 生成线程发现队列容量>10,生产者线程就进入waiting状态，一旦成功往队列添加数据，那么就唤醒所有线程（主要是生产者线程起来消费）。
 *
 * 消费线程消费时，发现队列容量==0，也会主动进入waiting状态。
 *
 *
 *
 * @author maxinze
 */
public class ProducerAndCustomer {

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    public static void main(String[] args) {

        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

        ThreadFactory threadFactory = new NameTreadFactory();

        RejectedExecutionHandler handler = new MyIgnorePolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);

        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        Queue<Integer> queue = new LinkedList<>();

        final Customer customer = new Customer(queue);
        final Producer producer = new Producer(queue);

//        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Integer a = customer.removeObject();
                    System.out.println("消费了数据 " + a);
                }
            });

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random random = new Random();
                    Integer a = random.nextInt(1000);
                    System.out.println("生成了数据 " + a);
                    producer.addObject(a);
                }
            });
        }
    }

    private static class Customer {
        Queue<Integer> queue;

        Customer(Queue<Integer> queue) {
            this.queue = queue;
        }

        public Integer removeObject() {
            synchronized (queue) {
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列中没有元素了，进行等待");
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer number = queue.poll();
                System.out.println("唤醒所有生产线程，当前queue大小是" + queue.size());
                queue.notifyAll();
                return number;
            }
        }
    }

    private static class Producer {
        Queue<Integer> queue;

        Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        public void addObject(Integer number) {
            synchronized (queue) {
                try {
                    while (queue.size() > 10) {
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(number);
                queue.notifyAll();
                System.out.println("唤醒所有消费线程，当前queue大小是" + queue.size());
            }
        }
    }

}
