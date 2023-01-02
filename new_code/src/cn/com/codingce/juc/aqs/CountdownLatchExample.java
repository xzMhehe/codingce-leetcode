package cn.com.codingce.juc.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 5;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        int i = 0;
        for (; i < totalThread; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.print("run.." + finalI + " ");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        //executorService.shutdown();
    }
}
