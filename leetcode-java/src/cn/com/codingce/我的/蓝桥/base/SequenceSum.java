package cn.com.codingce.我的.蓝桥.base;

import java.util.Scanner;

/**
 * 序列求和
 *
 * @author williamma
 */
public class SequenceSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        System.out.println(n * (1 + n) / 2);
//        IntStream.range(1, n + 1).sum();
        sc.close();

    }

}
