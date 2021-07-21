package cn.com.codingce.数组.非递减数列665;

/**
 * 665. 非递减数列
 * <p>
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkPossibility(new int[]{1, 2, 3}));
    }

    public boolean checkPossibility(int[] nums) {

        int len = nums.length;

        int left = 0, right = len - 1;

        while (left < len - 1 && nums[left] <= nums[left + 1])
            left++;


        if (left == len - 1)
            return true;

        while (right >= 1 && nums[right] >= nums[right - 1])
            right--;

        if (left + 1 != right)
            return false;


        if (left == 0 || right == len - 1)
            return true;

        return nums[left - 1] <= nums[right] || nums[left] <= nums[right + 1];
    }

}
