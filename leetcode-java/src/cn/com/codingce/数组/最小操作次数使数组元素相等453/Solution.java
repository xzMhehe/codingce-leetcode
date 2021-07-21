package cn.com.codingce.数组.最小操作次数使数组元素相等453;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * <p>
 * 找到所有出现两次的元素。
 * <p>
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }


    public List<Integer> findDuplicates(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                list.add(nums[map.get(nums[i])]);
                continue;
            }
            map.put(nums[i], i);
        }
        return list;
    }
}
