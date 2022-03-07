package cn.com.codingce.双指针.和为S的两个数字;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().FindNumbersWithSum(new int[]{1, 2, 4, 3, 9}, 3));
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> ret = new ArrayList<>();
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (sum == array[left] + array[right]) {
                ret.add(array[left]);
                ret.add(array[right]);
                return ret;
            } else if (sum > array[left] + array[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ret;
    }
}
