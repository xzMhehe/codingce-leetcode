package cn.com.codingce.lq.test;

public class Rectangle {

    private int c;
    private int k;

    public void Mj() {
        System.out.println("底面积：" + this.c * this.k);
    }


    public Rectangle() {
    }

    public Rectangle(int c, int k) {
        this.c = c;
        this.k = k;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "c=" + c +
                ", k=" + k +
                '}';
    }
}
