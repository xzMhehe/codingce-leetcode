package cn.com.codingce.双指针.左旋转字符串;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(new Solution().LeftRotateString("abcXYZdef", 3));
        System.out.println(new Solution().LeftRotateString("abc", 10));
    }

    public String LeftRotateString(String str, int n) {
        // 边界
        if (str.length() == 0 || str == null) {
            return "";
        }
        // n > str.length()
        if (str.length() < n) {
            n = str.length() / 2;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) swap(chars, i++, j--);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public String LeftRotateString2(String str, int n) {
        // 解法1优化：n取模来避免无用遍历

        // 边界
        if (str.length() == 0 || str == null) {
            return "";
        }
        // 取模
        n %= str.length();
        // 创建sb
        StringBuilder sb = new StringBuilder(str);
        // 循环n次，每次左移一位
        while (n > 0) {
            // 取第一位
            char temp = sb.charAt(0);
            // 删除第一位
            sb.deleteCharAt(0);
            // 添加至最后一位
            sb.append(temp);

            n--;
        }

        return sb.toString();

    }
}
