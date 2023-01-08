package cn.com.codingce.juc.生产者消费者模型;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * {@link PipedInputStream} {@link PipedOutputStream}
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class PipedInputStreamExample {

    final PipedInputStream pis = new PipedInputStream();
    final PipedOutputStream pos = new PipedOutputStream();

    {
        try {
            pis.connect(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PipedInputStreamExample example = new PipedInputStreamExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(15L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    int num = (int) (Math.random() * 10);
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产了一个数字, 该数字为: " + num + " ***************");
                    pos.write(num);
                    pos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    int num = pis.read();
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费了一个数字, 该数字为: " + num + " ---------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
