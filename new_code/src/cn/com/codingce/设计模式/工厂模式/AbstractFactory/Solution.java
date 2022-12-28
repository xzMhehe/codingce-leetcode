package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

/**
 * AbstractFactory（抽象工厂）声明了一组用于创建对象的方法，注意是一组。
 * ConcreteFactory（具体工厂）：它实现了在抽象工厂中声明的创建对象的方法，生成一组具体对象。
 * AbstractProduct（抽象产品）：它为每种对象声明接口，在其中声明了对象所具有的业务方法。
 * ConcreteProduct（具体产品）：它定义具体工厂生产的具体对象。
 *
 *
 *
 * @author mxz
 */
public class Solution {
    public static void main(String[] args) {
        // 抽象工厂
        SystemFactory mFactory;
        // Android 具体工厂
        mFactory = new AndroidFactory();

        // 抽象产品
        OperationController operationController;
        UIController uiController;

        // 具体产品
        operationController = mFactory.createOperationController();
        uiController = mFactory.createUIController();

        // 调用
        operationController.control();
        uiController.display();
    }
}
