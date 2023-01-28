package cn.com.codingce.设计模式.工厂模式.SimpleFactory;

/**
 * 圆形
 */
public class CircleShape implements Shape{
    @Override
    public void draw() {
        System.out.println("CircleShape");
    }

    public CircleShape() {
        System.out.println("CircleShape create");
    }
}
