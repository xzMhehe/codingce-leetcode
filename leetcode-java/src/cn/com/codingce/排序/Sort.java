package cn.com.codingce.排序;

import java.util.Arrays;

/**
 * @author maxinze
 */
public class Sort {


    public static void main(String[] args) {
        Sort sort = new Sort();
        System.out.println(Arrays.toString(sort.bubbleSort(new int[]{1, 9, 2, 5, 8})));
        System.out.println(Arrays.toString(sort.selectSort(new int[]{1, 9, 2, 5, 8})));
        System.out.println(Arrays.toString(sort.insertSort(new int[]{1, 9, 2, 5, 8})));
        System.out.println(Arrays.toString(sort.shellSort(new int[]{1, 9, 2, 5, 8})));
        System.out.println(Arrays.toString(sort.quickSort(new int[]{2, 7, 4, 5, 10, 1, 9, 3, 8, 6}, 0, 9)));

    }


    /**
     * 冒泡
     * <p>
     * 时间复杂度 O(n²)    稳定
     *
     * @param nums
     */
    public int[] bubbleSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * <p>
     * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。
     *
     * @param nums
     * @return
     */
    public int[] selectSort(int[] nums) {
        int length = nums.length;
        int minIndex, temp;
        // 1, 9, 2, 5, 8
        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = 0; j < length; j++) {
                // 寻找最小的数
                if (nums[j] > nums[minIndex]) {
                    // 将最小数的索引保存
                    minIndex = j;
                }
                temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     * <p>
     * O(n²)
     *
     * @param nums
     * @return
     */
    public int[] insertSort(int[] nums) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < nums.length; i++) {
            // 记录要插入的数据
            int tmp = nums[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            // 1, 9, 2, 5, 8
            while (j > 0 && tmp < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                nums[j] = tmp;
            }
        }
        return nums;
    }

    /**
     * 希尔排序：希尔排序，也称 递减增量 排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
     *
     * @param nums
     * @return
     */
    public int[] shellSort(int[] nums) { //1, 9, 2, 5, 8
        int len = nums.length; // 5
        int temp;
        for (int step = len / 2; step >= 1; step /= 2) { // step = 2
            for (int i = step; i < len; i++) { // 2
                temp = nums[i];
                int j = i - step; // 0
                while (j >= 0 && nums[j] > temp) {
                    nums[j + step] = nums[j];
                    j -= step;
                }
                nums[j + step] = temp;
            }
        }
        return nums;
    }

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int[] mergeSort(int[] nums) {


        return nums;
    }

    /**
     * 快速排序
     * <p>
     * 快速排序是由东尼·霍尔所发展的一种排序算法。在平均状况下，排序 n 个项目要 Ο(nlogn) 次比较。
     * 在最坏状况下则需要 Ο(n^2) 次比较，但这种状况并不常见。事实上，快速排序通常明显比其他 Ο(nlogn) 算法更快，
     * 因为它的内部循环（inner loop）可以在大部分的架构上很有效率地被实现出来。
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int[] quickSort(int[] nums, int low, int high) {
        int p, i, j, temp;
        if (low > high) {
            return nums;
        }
        // p 就是基准数，这里 就是每个数组的第一个
        p = nums[low]; // 2
        i = low; // 0
        j = high; // 9
        while (i < j) {
            // 右边发现小于p的值停止循环
            while (nums[j] >= p && i < j) { // 6 j = 5 i = 0
                j--;
            } //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）
            // 2, 7, 4, 5, 10, 1, 9, 3, 8, 6

            //左边当发现大于p的值时停止循环
            while (nums[i] <= p && i < j) { // 10 j = 5 i = 1
                i++;
            }
            if (i < j) { // j = 5 i = 1
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            } // 2, 1, 4, 5, 10, 7, 9, 3, 8, 6
        }
        // nums[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        nums[low] = nums[i]; // i = 4  nums[i] = 6   6, 7, 4, 5, 2, 1, 9, 3, 8, 10
        nums[i] = p;
        //对左边快排
        quickSort(nums, low, j - 1);
        //对右边快排
        quickSort(nums, j + 1, high);
        return nums;
    }


}
