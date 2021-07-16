package cn.com.codingce.myarray.oen;

import java.util.Arrays;

/**
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().rotate(new int[]{1, 2, 3, 4, 5}, 3);
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
            System.out.println((i + k) % n + " ");
        }
        System.out.println(Arrays.toString(newArr));
        System.out.println(Arrays.toString(nums));

        System.arraycopy(newArr, 0, nums, 0, n);

    }
}
