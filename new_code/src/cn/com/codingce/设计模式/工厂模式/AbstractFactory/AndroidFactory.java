package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

public class AndroidFactory implements SystemFactory {
    @Override
    public OperationController createOperationController() {
        return new AndroidOperationController();
    }
}
