package cn.com.codingce.我的.天理.juc;

import java.util.concurrent.Semaphore;

public class MSemaphore {

    public void test() {
        // 创建一个计数阈值为 5 的信号量对象
        // 只能 5 个线程同时访问
        Semaphore semp = new Semaphore(5);
        try { // 申请许可
            semp.acquire();
            try {
                // 业务逻辑
            } catch (Exception e) {
            } finally {
                // 释放许可
                semp.release();
            }
        } catch (InterruptedException e) {
        }
    }

}
