package cn.com.codingce.排序.冒泡排序;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BubbleSort().sort(new int[]{0, 2, 1, 5, 7, 6, 3})));
    }

    public int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 设置标记，j循环未交换则退出本次循环，
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            // 0, 2, 1, 5, 7, 6, 3
            // 0, 1, 2, 5, 7, 6, 3
            // 0, 1, 2, 5, 7, 6, 3
            // 0, 1, 2, 5, 7, 6, 3
            // 0, 1, 2, 5, 6, 7, 3
            // 0, 1, 2, 5, 6, 3, 7
            if (flag) {
                break;
            }
        }
        return arr;
    }
}
