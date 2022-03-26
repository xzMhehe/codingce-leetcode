package cn.com.codingce.设计模式.单例模式;

/**
 * 懒汉模式（线程不安全）
 *
 * @author inke219223m
 */
public class Singleton_01 {

    private static Singleton_01 instance;
    private Singleton_01() {}

    public static Singleton_01 getInstance() {
        if (null != instance) return instance;
        instance = new Singleton_01();
        return instance;
    }

    public static void main(String[] args) {


    }

}
