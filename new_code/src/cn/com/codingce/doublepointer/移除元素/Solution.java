package cn.com.codingce.doublepointer.移除元素;

/**
 * 移除元素
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeElement(new int[]{1, 2, 5, 2, 3, 1}, 2));
    }

    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        int len = nums.length;
        while (fast < len) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
