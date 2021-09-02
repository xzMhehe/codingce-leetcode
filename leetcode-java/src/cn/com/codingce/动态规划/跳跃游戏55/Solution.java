package cn.com.codingce.动态规划.跳跃游戏55;

/**
 * 55. 跳跃游戏
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 链接：https://leetcode-cn.com/problems/jump-game
 *
 * 贪心
 *
 * @author maxinze
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().canJump(new int[]{1, 2, 3, 2, 1, 0, 4, 1}));
        System.out.println(new Solution().canJump2(new int[]{1, 2, 3, 2, 1, 4, 1}));
        System.out.println(new Solution().canJump3(new int[]{1, 2, 3, 2, 1, 4, 1}));
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * DP
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        // state: 记录能否到达
        boolean[] dp = new boolean[nums.length];

        // initialize
        dp[0] = true;

        // function
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // 最后一位能否达到
        return dp[nums.length - 1];
    }

    /**
     * 贪心
     *
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        // corner case
        if (nums.length == 1) {
            return true;
        }

        // 第一步能跳跃的范围
        int range = nums[0];
        for (int i = 0; i <= range; i++) {
            // 不断更新最大跳跃范围
            range = Math.max(range, i + nums[i]);
            if (range >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }

}
