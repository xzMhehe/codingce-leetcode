package cn.com.codingce.sort;

import java.util.Scanner;

/**
 * 啊哈桶排序
 *
 * @author williamma
 */
public class Tong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[10];
        for (int i = 1; i <= 5; i++) {
            array[sc.nextInt()]++;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < array[i]; j++) {
                System.out.print(i +  " ");
            }
        }

    }
}
