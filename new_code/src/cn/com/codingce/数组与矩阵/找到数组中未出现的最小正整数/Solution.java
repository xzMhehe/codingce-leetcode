package cn.com.codingce.数组与矩阵.找到数组中未出现的最小正整数;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * 找到数组中未出现的最小正整数
     * <p>
     * return the min number
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int minNumberdisappered(int[] arr) {
        // write code here
        int min = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
            while (set.contains(min)) {
                min++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minNumberdisappered(new int[]{-1, 1, 2, 3, 4}));
    }
}
