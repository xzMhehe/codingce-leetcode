package cn.com.codingce.双指针.删除有序数组中的重复项;

/**
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 6}));
    }

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        int fast = 0, slow = 0;
        while (fast < len) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return 1 + slow;
    }

}
