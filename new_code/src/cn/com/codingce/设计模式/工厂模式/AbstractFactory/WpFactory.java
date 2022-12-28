package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

public class WpFactory implements SystemFactory {
    @Override
    public OperationController createOperationController() {
        return new WpOperationController();
    }

    @Override
    public UIController createUIController() {
        return new WpUIController();
    }

}
