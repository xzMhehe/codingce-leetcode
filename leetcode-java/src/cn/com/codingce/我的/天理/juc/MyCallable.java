package cn.com.codingce.我的.天理.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<String> {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //启动多线程
        Future<String> future = threadPool.submit(new MyCallable());
        try {
            System.out.println("waiting thread to finish");
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        return "Callable创建线程";
    }
}
