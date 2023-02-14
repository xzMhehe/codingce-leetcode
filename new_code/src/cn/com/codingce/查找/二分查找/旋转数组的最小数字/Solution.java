package cn.com.codingce.查找.二分查找.旋转数组的最小数字;

public class Solution {

    public static void main(String[] args) {

    }

    public int minNumberInRotateArray(int[] nums) {
        if (nums.length == 0) return 0;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[l] == nums[m] && nums[m] == nums[h]) return minNumber(nums, l, h);
            else if (nums[m] <= nums[h]) h = m;
            else l = m + 1;
        }
        return nums[l];
    }

    private int minNumber(int[] nums, int l, int h) {
        for (int i = l; i < h; i++)
            if (nums[i] > nums[i + 1]) return nums[i + 1];
        return nums[l];
    }
}
