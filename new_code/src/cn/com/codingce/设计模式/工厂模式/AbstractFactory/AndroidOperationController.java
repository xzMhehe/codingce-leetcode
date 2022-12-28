package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

public class AndroidOperationController implements OperationController {
    @Override
    public void control() {
        System.out.println("AndroidOperationController");
    }
}
