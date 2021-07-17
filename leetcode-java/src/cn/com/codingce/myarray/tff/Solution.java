package cn.com.codingce.myarray.tff;

import java.util.Arrays;

/**
 * 344. 反转字符串
 */
public class Solution {
    public static void main(String[] args) {


    }


    public void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }
}
