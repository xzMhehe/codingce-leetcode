package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

public class AndroidUIController implements UIController {
    @Override
    public void display() {
        System.out.println("AndroidUIController");
    }
}
