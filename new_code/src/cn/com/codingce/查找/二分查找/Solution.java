package cn.com.codingce.查找.二分查找;

public class Solution {

    public static void main(String[] args) {
        System.out.println(binSearch(new int[]{2, 5, 6, 8, 10}, 8));
    }

    /**
     * 非递归
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        // 防止数组越界, 且位运算比除法运算快
        int ret = l + (r - l) >> 1;
        while (l < r) {
            if (nums[ret] == target) {
                return 1;
            } else if (nums[ret] > target) {
                r = ret;
                ret = (r + l) / 2;
            } else {
                l = ret;
                ret = (r + l) / 2;
            }
        }
        return 0;
    }

    /**
     * 递归
     *
     * @param nums
     * @param l
     * @param r
     * @param target
     * @return
     */
    public static int binSearchD(int[] nums, int l, int r, int target) {
        int ret = l + (r - l) >> 1;
        if (nums[ret] == target) {
            return 1;
        }
        if (l >= r) {
            return -1;
        }
        if (nums[ret] > target) {
            return binSearchD(nums, l, ret, target);
        } else {
            return binSearchD(nums, ret, r, target);
        }
    }
}
