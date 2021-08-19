package cn.com.codingce.我的.天理.juc;

public class MyThread extends Thread {

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        myThread1.start();
    }
    @Override
    public void run() {
        System.out.println("MyThread.run()");
    }
}
