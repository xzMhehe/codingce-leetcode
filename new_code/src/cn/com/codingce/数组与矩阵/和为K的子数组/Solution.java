package cn.com.codingce.数组与矩阵.和为K的子数组;

public class Solution {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{1, 1, 2}, 3));
    }
}
