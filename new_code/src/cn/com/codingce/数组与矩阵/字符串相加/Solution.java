package cn.com.codingce.数组与矩阵.字符串相加;

public class Solution {

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            // charAt char 类型需要 - '0' 或 - 48
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {

    }

    /**
     * 睿企招聘 手撕面试题
     * <p>
     * ### 字符串加法
     * 输出：`"9999999999135790"`
     * 输入：`"123456789012345"`,`"9876543210123445"`
     * 输入两个长度不等的字符串（可能会超过long的最大值），计算两个字符串的数值和，不能直接强制转换成 int long bigDecimal
     *
     * @param num1
     * @param num2
     * @return
     */
    public String stringAdd(String num1, String num2) {
        int bl = num2.length();
        int al = num1.length();
        StringBuilder ret = new StringBuilder();
        int i = 0, numA = 0, numB = 0, r = 0, y = 0;
        while (i < num1.length() || i < num2.length()) {
            if (al - 1 >= 0) {
                numA = num1.charAt(al - 1) - 48;
                --al;
            } else {
                numA = 0;
            }
            if (bl - 1 >= 0) {
                numB = num2.charAt(bl - 1) - 48;
                --bl;
            } else {
                numB = 0;
            }
            if (y > 0) r = numA + numB + y;
            else r = numA + numB;
            if (r >= 10) {
                r = r % 10;
                y = 1;
            } else {
                y = 0;
            }
            ret.append(r);
            i++;
        }
        return ret.reverse().toString();
    }

}
