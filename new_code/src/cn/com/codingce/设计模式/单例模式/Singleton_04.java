package cn.com.codingce.设计模式.单例模式;

/**
 * 使用类的静态内部类实现的单例模式，保证了线程安全，也保证了懒加载，也不会因为加索的方式耗费性能
 * 这主要是因为JVM虚拟机保证多线程并发访问正确性，一个类的构造方法在多线程环境可以被正确加载
 * 推荐使用
 */
public class Singleton_04 {
    private static class SingletonHolder {
        private static Singleton_04 instance = new Singleton_04();
    }

    private Singleton_04() {
    }

    public static Singleton_04 getInstance() {
        return SingletonHolder.instance;
    }
}
