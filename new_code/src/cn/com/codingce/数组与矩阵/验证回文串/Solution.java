package cn.com.codingce.数组与矩阵.验证回文串;

public class Solution {

    /**
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * <p>
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
        // 字符不是字母和数字的情况
        if (!Character.isLetterOrDigit(s.charAt(l))) {
            l++;
        } else if (!Character.isLetterOrDigit(s.charAt(r))) {
            // 字符不是字母和数字的情况
            r--;
        } else {
            // 判断二者是否相等. toLowerCase 强转成小写字母
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                return false;
            l++;
            r--;
        }
    }

    return true;
}

}
