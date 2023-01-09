package cn.com.codingce.juc.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * {@link ArrayBlockingQueue}
 * <p>
 * 详细请看生产者消费者模型
 *
 * @author mxz
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        // 创建一个阻塞队列
        final BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);
    }
}
