package cn.com.codingce.myarray.ffs;

/**
 * 557. 反转字符串中的单词 III
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().reverseWords("Let's take LeetCode contest"));

    }

    public String reverseWords(String s) {
        String[] str = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            for (int j = str[i].length() - 1; j >= 0; j--) {
                sb.append(str[i].charAt(j));
            }

            if (i != str.length - 1) sb.append(' ');

        }

        return sb.toString();

    }

}
