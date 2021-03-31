package cn.com.codingce.lq.test;

import java.util.Arrays;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        int[] array = new int[7];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            System.out.print("请输入第" + (i + 1)  + "个整数：");
            array[i] = sc.nextInt();
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7 - i - 1; j++) {
                if(array[j + 1] > array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        Arrays.stream(array).forEach(e -> {
            System.out.print(e + " ");
        });

    }
}
