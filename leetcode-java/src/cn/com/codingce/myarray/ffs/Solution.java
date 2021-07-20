package cn.com.codingce.myarray.ffs;

/**
 * 557. 反转字符串中的单词 III
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().reverseWords2("Let's take LeetCode contest"));

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

    /**
     * 此时的原地解法的意义就失去了，因为又开辟出一块空间
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        StringBuffer sb = new StringBuffer(s);
        int len = sb.length();
        int i = 0;
        while (i < len) {
            int start = i;
            while (i < len && sb.charAt(i) != ' ') {
                i++;
            }

            int left = start, right = i - 1;
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left, sb.charAt(right));
                sb.setCharAt(right, tmp);

                left++;
                right--;
            }

            while (i < len && sb.charAt(i) == ' ') {
                i++;
            }
        }

        return sb.toString();
    }


}
