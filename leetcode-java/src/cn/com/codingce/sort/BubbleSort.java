package cn.com.codingce.sort;

import java.util.Arrays;

/**
 * 冒泡算法
 *
 * 算法分析
 * 空间复杂度 O(1)、时间复杂度 O(n²)。
 *
 * 分情况讨论：
 *
 * 给定的数组按照顺序已经排好：只需要进行 n-1 次比较，两两交换次数为 0，时间复杂度为 O(n)，这是最好的情况。
 * 给定的数组按照逆序排列：需要进行 n*(n-1)/2 次比较，时间复杂度为 O(n²)，这是最坏的情况。
 * 给定的数组杂乱无章。在这种情况下，平均时间复杂度 O(n²)。
 * 因此，时间复杂度是 O(n²)，这是一种稳定的排序算法。
 *
 * 稳定是指，两个相等的数，在排序过后，相对位置保持不变。
 *
 * @author mxz
 */
public class BubbleSort {

    private static void bubbleSort(int[] nums) {
        boolean hasChange = true;
        for (int i = 0, n = nums.length; i < n - 1 && hasChange; ++i) {
            hasChange = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChange = true;
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 9, 5, 8};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
