package cn.com.codingce.设计模式.工厂模式.SimpleFactory;

/**
 * 简单工厂
 *
 * 1、具体工厂
 * 2、抽象产品
 * 3、具体产品
 *
 * @author mxz
 */
public class ShapeFactory {
    public static Shape getShap(String type) {
        Shape shape = null;
        switch (type) {
            case "circle":
                shape = new CircleShape();
                break;
            case "rect":
                shape = new RectShape();
                break;
            case "triangle":
                shape = new TriangleShape();
                break;
            default:
        }

        return shape;
    }

    public static void main(String[] args) {
        Shape shape = ShapeFactory.getShap("circle");
        shape.draw();
    }
}
