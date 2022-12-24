package cn.com.codingce.数组与矩阵.找到和最大的长度为K的子序列;

import java.util.Arrays;

/**
 * 2099. 找到和最大的长度为 K 的子序列
 * <p>
 * 给你一个整数数组nums和一个整数k。你需要找到nums中长度为 k的 子序列，且这个子序列的和最大。
 * 请你返回 任意 一个长度为k的整数子序列。
 * 子序列定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。
 * <p>
 * 输入：nums = [2,1,3,3], k = 2
 * 输出：[3,3]
 * 解释：
 * 子序列有最大和：3 + 3 = 6 。
 */
public class Solution {
    // nums = [3,4,3,3], k = 2
    public int[] maxSubsequence(int[] nums, int k) {
        // 4
        int len = nums.length;
        // idxMap：辅助数组，用来存储数值和索引
        int[][] idxMap = new int[len][2];
        for (int i = 0; i < len; i++) {
            idxMap[i][1] = nums[i];
            idxMap[i][0] = i;
        }
        // 按照数值nums[idx]从大到小排序
        Arrays.sort(idxMap, (a, b) -> b[1] - a[1]);
        System.out.println("=====sort");
        for (int[] num : idxMap) {
            for (int n : num) {
                System.out.print(n + "\t");
            }
            System.out.println();
        }
        // 按照索引idx从小到大进行排列
        Arrays.sort(idxMap, 0, k, (a, b) -> a[0] - b[0]);
        System.out.println("=====sort2");
        for (int[] num : idxMap) {
            for (int n : num) {
                System.out.print(n + "\t");
            }
            System.out.println();
        }
        // 复制结果
        int[] res = new int[k];
        for (int idx = 0; idx < k; idx++) {
            // 返回值
            res[idx] = idxMap[idx][1];
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution().maxSubsequence(new int[]{-1, -2, 3, 4}, 3);
    }

}
