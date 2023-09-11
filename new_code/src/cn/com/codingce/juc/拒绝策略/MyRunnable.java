package cn.com.codingce.juc.拒绝策略;

public class MyRunnable implements Runnable {

    private final String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " is running. currentThreadName:" + Thread.currentThread().getName());
            Thread.sleep(200L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
