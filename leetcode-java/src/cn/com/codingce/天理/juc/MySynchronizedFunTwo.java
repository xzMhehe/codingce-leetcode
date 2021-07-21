package cn.com.codingce.天理.juc;

public class MySynchronizedFunTwo {

    public static void main(String[] args) {

        MySynchronizedFunTwo synchronizedFun1 = new MySynchronizedFunTwo();
        MySynchronizedFunTwo synchronizedFun2 = new MySynchronizedFunTwo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedFun1.runing1(Thread.currentThread());
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedFun2.runing2(Thread.currentThread());
            }
        });
        thread2.start();
    }

    public synchronized void runing1(Thread thread) {
        System.out.println(thread.getName() + " 1 得到锁");
        System.out.println("------ 1 is running ------");
        working();
        System.out.println(thread.getName() + " 1 释放锁");
        System.out.println();
    }

    public synchronized void runing2(Thread thread) {
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
