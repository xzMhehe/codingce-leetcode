package cn.com.codingce.juc;

/**
 * 修饰一个代码块
 *
 * @author williamma
 */
public class MySynchronizedCodeOne implements Runnable {
    public static void main(String[] args) {

        MySynchronizedCodeOne mySynchronizedCodeOne = new MySynchronizedCodeOne();

        Thread thread1 = new Thread(mySynchronizedCodeOne, "SyncThread1");
        Thread thread2 = new Thread(mySynchronizedCodeOne, "SyncThread2");
        thread1.start();
        thread2.start();

        ///
        MySynchronizedCodeOne mySynchronizedCodeOne3 = new MySynchronizedCodeOne();
        MySynchronizedCodeOne mySynchronizedCodeOne4 = new MySynchronizedCodeOne();
        Thread thread3 = new Thread(mySynchronizedCodeOne3, "SyncThread3");
        Thread thread4 = new Thread(mySynchronizedCodeOne4, "SyncThread4");
        thread3.start();
        thread4.start();
    }

    private static int count;

    public MySynchronizedCodeOne() {
        count = 0;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程 " + Thread.currentThread().getName() + " : " + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getCount() {
        return count;
    }
}
