package cn.com.codingce.我的.天理.juc;

public class ThreadTarget implements Runnable {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "线程执行了main方法");
        ThreadTarget target = new ThreadTarget();
        Thread thread = new Thread(target);
        thread.start();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程执行了run方法");
            }
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程执行了run方法");
        });
        thread2.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程执行了run方法");
    }
}
