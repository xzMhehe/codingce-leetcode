package cn.com.codingce.myarray.twonumadd;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xzMa
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 */
public class TwoSumTest {
    public static void main(String[] args) {
        int[] nums = {11, 15, 2, 7};
        nums = twoSum(nums, 9);
        for (int i : nums) {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // map是一个key和value的键值对的集合。有key和value键值对，就会有判断是否有key。这方法就是containsKey方法。
            int complement = target - nums[i];
            // map中的containsKey（key）方法是判断该key在map中是否有key存在。如果存在则返回true。如果不存在则返回false。
            if (map.containsKey(complement)) {
                //Java 集合类中的 Map.get() 方法返回指定键所映射的值。如果此映射不包含该键的映射关系，则返回 null。
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}