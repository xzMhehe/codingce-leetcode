package cn.com.codingce.设计模式.工厂模式.SimpleFactory;

/**
 * 三角形
 */
public class TriangleShape implements Shape {
    public TriangleShape() {
        System.out.println("TriangleShape create");
    }

    @Override
    public void draw() {
        System.out.println("TriangleShape");
    }
}
