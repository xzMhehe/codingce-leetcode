package cn.com.codingce.蓝桥.base;

import java.util.Scanner;

public class Zero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double input = sc.nextDouble();
        double area = Math.pow(input, 2) * Math.PI;
//        area = area * 10000000 + 0.5;
        System.out.printf("%.7f", area);
        sc.close();


    }
}
