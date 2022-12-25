package cn.com.codingce.数组与矩阵.最长公共前缀;

/**
 * @author mxz
 */
public class Solution {
    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 横向扫描
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String ret = strs[0];
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            ret = longestCommonPrefix(ret, strs[i]);
            if (ret.length() == 0) {
                break;
            }
        }
        return ret;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }
}
