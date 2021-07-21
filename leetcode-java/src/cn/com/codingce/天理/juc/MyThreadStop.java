package cn.com.codingce.天理.juc;

public class MyThreadStop extends Thread {

    volatile boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println(getName() + " 正在运行 ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("week up for block...");
                stop = true; // 在异常处处理代码中修改共享变量状态
            }
        }
        System.out.println(getName() + "  退出... ");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadStop myThreadStop = new MyThreadStop();
        System.out.println(" 启动线程 ");
        myThreadStop.start();
        Thread.sleep(3000);
        System.out.println("中断线程...: " + myThreadStop.getName());
        myThreadStop.stop =  true; // 设置共享变量为true
        myThreadStop.interrupt(); // 阻塞时退出阻塞状态
        Thread.sleep(3000); // 主线程休眠3秒以便观察线程m1的中断情况
        System.out.println("Stopping application...");
    }
}
