package cn.com.codingce.sort;

import java.util.Arrays;

/**
 * 选择排序
 * <p>
 * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
 *
 * @author williamma
 */
public class SelectionSort {

    private static void selectionSort(int[] nums) {
        for (int i = 0, n = nums.length; i < n - 1; ++i) {
            int minIndex = i;

        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;

    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 6, 9, 7};
        System.out.println(Arrays.toString(nums));
    }
}
