package cn.com.codingce.数组与矩阵.数字在升序数组中出现的次数;

public class Solution {

    public static void main(String[] args) {

    }

    public int GetNumberOfK2(int[] array, int k) {
        // 暴力解法：遍历数组，找到目标数字计数器加一
        int ans = 0;
        for (int i = 0; i < array.length; i++) {
            //找到目标数字计数器加一
            if (array[i] == k) {
                ans++;
            }
        }
        // 返回结果
        return ans;
    }

    public int GetNumberOfK(int[] array, int k) {
        int leftIndex = binarySearch(array, k, true);
        int rightIndex = binarySearch(array, k, false) - 1;
        if (leftIndex <= rightIndex && rightIndex < array.length && array[leftIndex] == k && array[rightIndex] == k) {
            return rightIndex - leftIndex + 1;
        }
        return 0;
    }

    /**
     * 二分查找：参数：目标数组，目标值K，边界定义
     *
     * @param nums
     * @param k
     * @param lower
     * @return
     */
    public int binarySearch(int[] nums, int k, boolean lower) {
        // 关于二分查找有三种模板写法，具体取决于个人习惯
        int left = 0, right = nums.length - 1, ans = nums.length;
        // 不同的模板对left,right的初始化和while循环的终止条件不同，应注意！！
        while (left <= right) {
            // 取中间值(防止数值过大引起的加法溢出写法)
            int mid = left + (right - left) / 2;
            // 边界判断
            if (nums[mid] > k || lower && nums[mid] >= k) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
