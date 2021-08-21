package cn.com.codingce.我的.天理.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTarget implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "线程执行了call方法");
        Thread.sleep(5000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(Thread.currentThread().getName() + "线程执行了main方法");

        CallableTarget callableTarget = new CallableTarget();

        FutureTask<Integer> task = new FutureTask<Integer>(callableTarget);

        Thread thread = new Thread(task);

        thread.start();

        //当前线程会阻塞，一直等到结果返回。
        Integer result = task.get();

        System.out.println("执行完毕，打印result=" + result);
        System.out.println("执行完毕");
    }
}
