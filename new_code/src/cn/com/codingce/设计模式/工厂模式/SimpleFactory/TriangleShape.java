package cn.com.codingce.设计模式.工厂模式.SimpleFactory;

public class TriangleShape implements Shape {
    public TriangleShape() {
        System.out.println("TriangleShape create");
    }

    @Override
    public void draw() {
        System.out.println("TriangleShape");
    }
}
