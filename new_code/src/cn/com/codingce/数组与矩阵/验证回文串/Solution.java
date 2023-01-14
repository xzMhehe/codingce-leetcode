package cn.com.codingce.数组与矩阵.验证回文串;

public class Solution {

    /**
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     *
     * 输入: "race a car"
     * 输出: false
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else {
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                    return false;
                l++;
                r--;
            }
        }

        return true;


    }
}
