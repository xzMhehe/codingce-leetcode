package cn.com.codingce.我的.蓝桥.test;

public class Triangle {

    public static void main(String[] args) {
        Triangle triangle = new Triangle(3, 4, 5);
        System.out.println(triangle.getArea());
        System.out.println(triangle.getGirth());
    }

    private int a;
    private int b;
    private int c;

    public int getGirth() {
        return a < a + b && b < a + c && c < a + b ? a + b + c : 0;
    }

    public double getArea() {
        int d = (a + b + c) / 2;

        return a < a + b && b < a + c && c < a + b ? Math.sqrt(d * (d - a) * (d - b) *(d - c)): 0;
    }

    public Triangle() {
    }

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
