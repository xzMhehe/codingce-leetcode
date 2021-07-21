package cn.com.codingce.蓝桥.test;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        int[] array = new int[6];
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            System.out.print("请输入第" + (i + 1)  + "个整数：");
            array[i] = sc.nextInt();
            sum += array[i];
        }
        System.out.println("这6个数之和：" + sum);
    }
}
