package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceExample {

    private final static String INIT_STR = "后端码匠";

    public static void main(String[] args) throws InterruptedException {

        final AtomicMarkableReference<String> amr = new AtomicMarkableReference<>(INIT_STR, false);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("当前线程: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(Math.abs((int) (Math.random() * 100)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String name = Thread.currentThread().getName();
                if (amr.compareAndSet(INIT_STR, name, amr.isMarked(), !amr.isMarked())) {
                    System.out.println(Thread.currentThread().getName() + " 修改了对象！");
                    System.out.println("新的对象为：" + amr.getReference());
                }
            });
        }

        executorService.shutdown();
        System.out.println(executorService.awaitTermination(3, TimeUnit.SECONDS));
        ;
    }
}
