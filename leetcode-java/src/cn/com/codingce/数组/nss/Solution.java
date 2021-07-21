package cn.com.codingce.数组.nss;

import java.util.Arrays;

/**
 *
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().sortedSquares(new int[]{1, 2, 3, -1, 0})));

    }

    public int[] sortedSquares(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        Arrays.sort(nums);

        return nums;
    }

}
