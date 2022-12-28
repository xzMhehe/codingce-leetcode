package cn.com.codingce.设计模式.工厂模式.FactoryMethod;

/**
 * 抽象工厂测试
 * <p>
 * 简单工厂只有一个统一的工厂类，而工厂方法是针对每个要创建的对象都会提供一个工厂类，
 * 这些工厂类都实现了一个工厂基类（本例中的ReaderFactory ）
 *
 * @author mxz
 */
public class Solution {
    public static void main(String[] args) {
        ReaderFactory readerFactory = new JpgReaderFactory();
        Reader reader = readerFactory.getReader();
        reader.read();
    }
}
