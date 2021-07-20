package cn.com.codingce.myarray.oss;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 167. 两数之和 II - 输入有序数组
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }


    public int[] twoSum(int[] numbers, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{map.get(target - numbers[i]) + 1, i + 1};
            } else {
                map.put(numbers[i], i);
            }
        }
        return new int[]{};
    }
}
