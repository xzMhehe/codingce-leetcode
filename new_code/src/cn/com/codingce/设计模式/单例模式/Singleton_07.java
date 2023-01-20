package cn.com.codingce.设计模式.单例模式;

/**
 * 枚举单例（线程安全）
 *
 * 这种方式在功能上与共有域方法相近，但更加简洁，无偿提供串行化机制，绝对防止对此实例化。
 * 但是在继承场景下不可用
 *
 * public void test() {
 * 	Singleton_07.INSTANCE.test();
 * }
 *
 * @author inke219223m
 */
public enum Singleton_07 {

    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }
}

class MainTest {

    public static void main(String[] args) {
        Singleton_07.INSTANCE.doSomething();
    }

}