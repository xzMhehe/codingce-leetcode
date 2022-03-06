package cn.com.codingce.数组与矩阵.第一个只出现一次的字符位置;

/**
 * 在一个长为 字符串中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
 *
 * @author inke219223m
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().firstUniqChar(""));
        System.out.println(new Solution().firstUniqChar("cc"));
    }

    public int FirstNotRepeatingChar1(String str) {
        int[] cnts = new int[128];
        for (int i = 0; i < str.length(); i++)
            cnts[str.charAt(i)]++;
        for (int i = 0; i < str.length(); i++)
            //条件 cnts[str.charAt(i)] == 1
            if (cnts[str.charAt(i)] == 1) return i;
        return -1;
    }

    public char FirstNotRepeatingChar(String s) {
        int[] cnts = new int[128];
        for (int i = 0; i < s.length(); i++)
            cnts[s.charAt(i)]++;
        for (int i = 0; i < s.length(); i++)
            //条件 cnts[str.charAt(i)] == 1
            if (cnts[s.charAt(i)] == 1) return s.charAt(i);
        return ' ';
    }

    public char firstUniqChar(String s) {
        if (s.equals("")) return ' ';
        int[] cnts = new int[128];
        for (int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (cnts[s.charAt(i)] == 1)
                return s.charAt(i);
        }
        return ' ';
    }


}
