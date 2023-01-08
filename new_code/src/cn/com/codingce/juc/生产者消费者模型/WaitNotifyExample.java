package cn.com.codingce.juc.生产者消费者模型;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者和消费者, wait()和notify()方式实现
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class WaitNotifyExample {

    private static Integer count = 0;
    private static final Integer maxCount = 10;
    private final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(10L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    // 缓冲区满调用wait()方法等待
                    while (Objects.equals(count, maxCount)) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产, 目前总共有: " + count + " ***************");
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    // 缓冲区为空调用wait()方法等待
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费, 目前总共有: " + count + " ---------------");
                    LOCK.notifyAll();
                }
            }
        }
    }

}
