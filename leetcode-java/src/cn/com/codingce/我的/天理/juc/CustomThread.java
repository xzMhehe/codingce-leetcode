package cn.com.codingce.我的.天理.juc;

/**
 * 这种方法就是通过自定义CustomThread类继承Thread类，重写run()方法，
 * 然后创建CustomThread的对象，然后调用start()方法，JVM会创建出一个新线程，
 * 并且为线程创建方法调用栈和程序计数器，此时线程处于就绪状态，当线程获取CPU时间片后，线程会进入到运行状态，会去调用run()方法。
 * 并且创建CustomThread类的对象的线程(这里的例子中是主线程)与调用run()方法的线程之间是并发的，
 * 也就是在执行run()方法时，主线程可以去执行其他操作。
 *
 * @author maxinze
 */
public class CustomThread extends Thread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "线程调用了main方法");
        for (int i = 0; i < 10; i++) {
            if (i == 1) {
                CustomThread customThread = new CustomThread();
                customThread.start();
                System.out.println(Thread.currentThread().getName() + "线程--i是" + i);
            }
        }
        System.out.println("main()方法执行完毕！");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程调用了run()方法");
        for (int j = 0; j < 5; j++) {
            System.out.println(Thread.currentThread().getName() + "线程--j是" + j);
        }
        System.out.println("run()方法执行完毕！");
    }
}
