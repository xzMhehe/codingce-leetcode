package cn.com.codingce.排序.选择排序;

import java.util.Arrays;

/**
 * 找到数组中最大的元素，与数组最后一位元素交换。当只有一个数时，则不需要选择了，因此需要n-1趟排序
 * 代码实现要点：两个for循环，外层循环控制排序的趟数，内层循环找到当前趟数的最大值，随后与当前趟数组最后的一位元素交换
 *
 * @author inke219223m
 */
public class SelectionSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SelectionSort().selectionSort(new int[]{1, 5, 2, 7, 3, 4, 9})));
    }

    public int[] selectionSort(int[] arr) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            // 记住当下标
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
