package cn.com.codingce.设计模式.单例模式;

/**
 * 饿汉模式（线程安全）
 *
 * 此模式在程序启动时直接运行加载，后续有外部需要使用的时候获取即可。
 * 无论程序中是否用到这样的类都会在程序启动之初创建。（这也是它的缺点，无意义地占用内存）
 *
 */
public class Singleton_03 {
    private static Singleton_03 instance = new Singleton_03();
    public Singleton_03(){};

    public static Singleton_03 getInstance() {
        return instance;
    }

}
