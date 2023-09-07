package cn.com.codingce.doublepointer.多数元素;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().majorityElement(new int[]{3, 2, 2, 2, 1, 2}));
    }

    public int majorityElement(int[] nums) {
        int target = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                target = num;
                count = 1;
            } else if (num == target) {
                count++;
            } else {
                count--;
            }
        }

        return target;
    }

}
