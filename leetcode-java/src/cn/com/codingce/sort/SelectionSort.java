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
        for (int i = 0; i < nums.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < nums.length; j++)//选择最小的
                if (nums[j] < nums[index])
                    index = j;
            //交换
            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
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
