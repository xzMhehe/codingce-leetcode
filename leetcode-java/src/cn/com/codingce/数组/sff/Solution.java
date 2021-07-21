package cn.com.codingce.数组.sff;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {


        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findErrorNums(new int[]{1, 2, 2, 4})));
        System.out.println(Arrays.toString(solution.findErrorNums(new int[]{3, 3})));
    }

    public int[] findErrorNums(int[] nums) {
        // 0 存储重复出现的数，1 存储丢失的数
        int[] result = new int[2];
        int[] array = new int[nums.length + 2];
        for (int i : nums) {
            // 如果不为 0，则表示该数已经出现过，进行记录
            if (array[i] != 0) {
                result[0] = i;
            }
            array[i] = 1;
        }
        for (int i = 1; i < array.length; i++) {
            // 如果为 0 则表示该数没有出现过，进行记录
            if (array[i] == 0) {
                result[1] = i;
                System.out.println(i);
                // 找到就推出循环
                break;
            }
        }
        return result;
    }

}
