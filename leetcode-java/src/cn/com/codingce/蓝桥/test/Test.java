package cn.com.codingce.蓝桥.test;

public class Test {
    public static void main(String[] args) {
        int a, b, c, result = 0;
        for (int i = 100; i < 1000; i++) {
            a = i % 10;
            b = i /10 % 10;
            c = i / 100;
            if (c > b && b > a) {
                ++result;
                System.out.println(i);
            }

        }
        System.out.println("共有" + result + "种");
    }
}
