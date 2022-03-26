package cn.com.codingce.设计模式.单例模式;

/**
 * 懒汉模式（线程安全）
 *
 * 此模式保证了线程安全，但是由于把锁加到了方法上，所有的访问都因为需要锁占用导致资源浪费。
 *
 * @author inke219223m
 */
public class Singleton_02 {

    private static Singleton_02 instance;
    private Singleton_02() {}

    public static synchronized Singleton_02 getInstance() {
        if (null != instance) return instance;
        instance = new Singleton_02();
        return instance;
    }

    public static void main(String[] args) {


    }

}
