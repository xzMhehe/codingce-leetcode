package cn.com.codingce.动态规划.最大子序和53;

/**
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author maxinze
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{1, 2, -1, -3, -4, 5, 1}));
    }

    public int maxSubArray(int[] nums) {
        int per = 0, maxAns = nums[0];
        for (int num : nums) {
            per = Math.max(per + num, num);
            maxAns = Math.max(per, maxAns);
        }

        return maxAns;
    }
}
