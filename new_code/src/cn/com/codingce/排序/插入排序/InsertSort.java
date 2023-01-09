package cn.com.codingce.排序.插入排序;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new InsertSort().insertSort(new int[]{1, 5, 2, 7, 3, 4, 9})));
    }

    private int[] insertSort(int[] sourceArray) {
        int len = sourceArray.length;
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < len; i++) {
            // 记录插入的数据
            int tmp = sourceArray[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < sourceArray[j - 1]) {
                sourceArray[j] = sourceArray[j - 1];
                j--;
            }
            // 存在比其小的, 插入
            if (j != i) {
                sourceArray[j] = tmp;
            }
        }

        return sourceArray;
    }

}
