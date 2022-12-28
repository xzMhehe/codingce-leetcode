package cn.com.codingce.设计模式.工厂模式.FactoryMethod;

public class GifReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new GifReader();
    }
}
