package cn.com.codingce.排序;

import java.util.Scanner;

public class Tong_1000 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[10000];
        for (int i = 1; i <= 10000; i++) {
            array[sc.nextInt()]++;
        }

        for (int i = 0; i <= 10000; i++) {
            for (int j = 0; j < array[i]; j++) {
                System.out.print(array[i] + " ");
            }
        }


    }
}
