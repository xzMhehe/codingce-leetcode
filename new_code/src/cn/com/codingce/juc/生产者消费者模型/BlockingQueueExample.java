package cn.com.codingce.juc.生产者消费者模型;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueExample {

    private static Integer count = 0;
    // 创建一个阻塞队列
    final BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        BlockingQueueExample example = new BlockingQueueExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
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
                try {
                    blockingQueue.put(1);
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + "生产者生产，目前总共有" + count + " ***************");
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                try {
                    blockingQueue.take();
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + "消费者消费，目前总共有" + count + " ---------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
