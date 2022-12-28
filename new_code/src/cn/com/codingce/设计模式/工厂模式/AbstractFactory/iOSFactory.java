package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

public class iOSFactory implements SystemFactory {
    @Override
    public OperationController createOperationController() {
        return new iOSOperationController();
    }
}
