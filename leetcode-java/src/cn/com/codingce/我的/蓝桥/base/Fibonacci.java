package cn.com.codingce.我的.蓝桥.base;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long F[] = new long[1000000];
        F[1] = 1;
        F[2] = 1;
        for (int i = 3; i <= n; i++) {
            F[i] = (((F[i - 1] + F[i - 2])) % 10007);
        }
        System.out.println(F[n]);
    }

}
