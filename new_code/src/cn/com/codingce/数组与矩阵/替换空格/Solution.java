package cn.com.codingce.数组与矩阵.替换空格;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().replaceSpace("1 2 3 3"));
    }

    /**
     * 替换空格
     *
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串
     * @return string字符串
     */
    public String replaceSpace(String s) {
        StringBuffer result = new StringBuffer(s);
        int p1 = result.length() - 1;
        for (int i = 0; i <= p1; i++) {
            if (result.charAt(i) == ' ')
                //这里是两个空格哈
                result.append("  ");
        }

        int p2 = result.length() - 1;
        while (p1 >= 0 && p2 > p1) {
            char c = result.charAt(p1--);
            if (c == ' ') {
                result.setCharAt(p2--, '0');
                result.setCharAt(p2--, '2');
                result.setCharAt(p2--, '%');
            } else {
                result.setCharAt(p2--, c);
            }
        }
        return result.toString();
    }
}
