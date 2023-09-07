package cn.com.codingce.doublepointer.删除有序数组中的重复项2;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 示例
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates(new int[]{1, 1, 1, 2, 3, 3, 4, 5, 5, 6}));
    }

    public int removeDuplicates(int[] nums) {
        // 数组中的一号和二号元素肯定不用删除
        int count = 2;
        for(int i = 2 ; i < nums.length ; i++) {
            if(nums[i] != nums[count-2]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

}
