package cn.com.codingce.排序.归并排序;

import java.util.Arrays;

/**
 * 归并排序（Merge sort）是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * <p>
 * 对半拆分, 分解, 合并
 *
 * @author inke219223m
 */
public class MergeSort {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(new MergeSort().sort(new int[]{0, 2, 1, 4, 3, 9})));

    }

    public int[] sort(int[] arr) {

        if (arr.length < 2) {
            return arr;
        }
        int middle = arr.length >> 1;

        // 左闭右开
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }


}
