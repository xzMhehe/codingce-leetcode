package cn.com.codingce.我的.天理.juc;

public class MyRunnable implements Runnable {
    public static void main(String[] args) {
        // 启动 MyThread，需要首先实例化一个 Thread，并传入自己的 MyThread 实例：
        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);

        thread.start();

        //事实上，当传入一个 Runnable target 参数给 Thread 后， Thread 的 run()方法就会调用 target.run()
//        public void run() {
//            if (target != null) {
//                target.run();
//            }
//        }
    }
    @Override
    public void run() {
        System.out.println("MyRunnable.run()");
    }


}
