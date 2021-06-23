package cn.com.codingce.juc;

public class MySynchronizedStaticOne {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                MySynchronizedStaticOne.runing1(Thread.currentThread());
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                MySynchronizedStaticOne.runing2(Thread.currentThread());
            }
        });
        thread2.start();
    }

    public static synchronized void runing1(Thread thread) {
        System.out.println(thread.getName() + " 1 得到锁");
        System.out.println("------ 1 is running ------");
        working();
        System.out.println(thread.getName() + " 1 释放锁");
        System.out.println();
    }

    public static synchronized void runing2(Thread thread) {
        System.out.println(thread.getName() + " 2 得到锁");
        System.out.println("------ 2 is running ------");
        working();
        System.out.println(thread.getName() + " 2 释放锁");
        System.out.println();
    }

    public static void working() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
