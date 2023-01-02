package cn.com.codingce.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread thread = new Thread(ft);
        thread.start();
        System.out.println(ft.get());
        System.out.println(Integer.MAX_VALUE);
    }
}

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        return 123;
    }
}
