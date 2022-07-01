package cn.com.codingce.排序.选择排序;

import java.util.Arrays;

/**
 * @author inke219223m
 */
public class SelectionSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SelectionSort().selectionSort(new int[]{1, 5, 2, 7, 3, 4, 9})));
    }


    public int[] selectionSort(int[] arr) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }
}
