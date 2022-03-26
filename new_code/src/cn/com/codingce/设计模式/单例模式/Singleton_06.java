package cn.com.codingce.设计模式.单例模式;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS[AtomicReference]（线程安全）
 *
 * java并发库提供了很多原子类支持并发访问数据安全性，AtomicReference可以封装引用一个实例，支持并发访问。
 * 使用CAS的好处就是不需要使用传统的加锁方式保证线程安全，而是依赖于CAS的忙等算法，依赖于底层硬件的时间。
 * 相对于其他锁的实现没有线程的切换和组测也就没有了额外的开销，可以支持比较大的并发性
 * 缺点就是如果一直没有获取到将会处于死循环中。
 *
 * @author inke219223m
 */
public class Singleton_06 {

    private static final AtomicReference<Singleton_06> INSTANCE = new AtomicReference<Singleton_06>();

    private static Singleton_06 instance;

    private Singleton_06() {
    }

    public static final Singleton_06 getInstance() {
        for (; ; ) {
            Singleton_06 instance = INSTANCE.get();
            if (null != instance) return instance;
            INSTANCE.compareAndSet(null, new Singleton_06());
            return INSTANCE.get();
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton_06.getInstance());
        System.out.println(Singleton_06.getInstance());
    }
}

