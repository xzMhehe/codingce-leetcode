package cn.com.codingce.排序.插入排序;

import java.util.Arrays;

/**
 * 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
 *
 * @author mxz
 */
public class ShellSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ShellSort().shellSort(new int[]{1, 5, 2, 7, 3, 4, 9})));
    }

    private int[] shellSort(int[] sourceArray) {
        int len = sourceArray.length;
        int temp;
        for (int step = len / 2; step >= 1; step /= 2) {
            for (int i = step; i < len; i++) {
                temp = sourceArray[i];
                // j - step 就是代表与它同组隔壁的元素
                int j = i - step;
                while (j >= 0 && sourceArray[j] > temp) {
                    sourceArray[j + step] = sourceArray[j];
                    j -= step;
                }
                sourceArray[j + step] = temp;
            }
        }
        return sourceArray;
    }

}
