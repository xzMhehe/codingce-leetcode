package cn.com.codingce.设计模式.工厂模式.FactoryMethod;

public class PngReaderFactory implements ReaderFactory {

    @Override
    public Reader getReader() {
        return new PngReader();
    }
}
