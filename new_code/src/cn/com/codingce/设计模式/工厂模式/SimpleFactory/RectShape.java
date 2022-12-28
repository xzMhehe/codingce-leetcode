package cn.com.codingce.设计模式.工厂模式.SimpleFactory;

/**
 * 正方形
 */
public class RectShape implements Shape {
    @Override
    public void draw() {
        System.out.println("RectShape");
    }

    public RectShape() {
        System.out.println("RectShape create");
    }
}
