package cn.com.codingce.设计模式.工厂模式.AbstractFactory;

public class Solution {
    public static void main(String[] args) {
        SystemFactory mFactory;
        OperationController operationController;
        // Android
        mFactory = new AndroidFactory();
        operationController = mFactory.createOperationController();
        operationController.control();
    }
}
