package cn.com.codingce.myarray.sns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * <p>
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 统计数组中的元素系列
 * <p>
 * <p>
 * <p>
 * 子数组的度等于原数组的度，我们需要找到出现次数最多的数字首次出现和最后一次出现的位置，这两个位置中间的子数组实际上就是要寻找的数组。
 * <p>
 * 我们首先构造一个HashMap，用于统计每一个数字出现的所有位置，并从HashMap中提取出出现次数最多的数字
 * <p>
 * 这里需要注意的是，出现次数最多的数字可能不止一个，因此我们需要寻找这些数字出现最右端位置与最左端位置的最小差值。
 *
 * @author williamma
 */
public class Solution {

    public static void main(String[] args) {

        System.out.println(new Solution().findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
        System.out.println(new Solution().findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 1}));
    }


    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> hashMap = new HashMap<>();
        int maxDegree = 1;
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.get(nums[i]).add(i);
                maxDegree = Math.max(maxDegree, hashMap.get(nums[i]).size());
            } else {
                List<Integer> x = new ArrayList<>();
                x.add(i);
                hashMap.put(nums[i], x);
            }
        }

        if (maxDegree == 1) return 1;

        int minElements = Integer.MAX_VALUE;


        for (int k : hashMap.keySet()) {
            if (maxDegree == hashMap.get(k).size()) {
                List<Integer> list = hashMap.get(k);
                minElements = Math.min(minElements, list.get(list.size() - 1) - list.get(0) + 1);
            }
        }
        return minElements;
    }
}
