package cn.com.codingce.lq.test;

public class Cuboid extends Rectangle {

    public static void main(String[] args) {
        Rectangle cuboid = new Cuboid(3, 4, 5);
        cuboid.Mj();
    }

    private int g;

    @Override
    public void Mj() {
        super.Mj();
        System.out.println("体积：" + super.getC() * super.getK() * g);
    }

    public Cuboid() {
    }

    public Cuboid(int g) {
        this.g = g;
    }


    public Cuboid(int c, int k, int g) {
        super(c, k);
        this.g = g;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "g=" + g +
                '}';
    }
}
