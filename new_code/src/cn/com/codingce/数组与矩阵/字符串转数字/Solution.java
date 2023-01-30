package cn.com.codingce.数组与矩阵.字符串转数字;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().strToInt(" "));
    }

    public int strToInt(String str) {
        int MAX_INT = Integer.MAX_VALUE;
        int MIN_INT = Integer.MIN_VALUE;
        char[] arrayA = str.toCharArray();
        int n = 0;
        if (str.equals(""))     // 判断输入是否为空
            return 0;
        int i = 0;
        while (i < arrayA.length && arrayA[i] == ' ')
            i++;
        if (i == arrayA.length) return 0;
        int sign = 1;   // 用于判定输入字符串数字的正负, 初始化为1表示为正数
        if (arrayA[i] == '+' || arrayA[i] == '-') {
            if (arrayA[i] == '-') sign = -1;
            i++;
        }
        while (i < arrayA.length && Character.isDigit(arrayA[i])) {  // 确定是数字0~9才执行循环
            int c = arrayA[i] - '0';
            // 当输入字符串表示数为正数, 且大于MAX_INT
            if (sign > 0 && (n > MAX_INT / 10 || (n == MAX_INT / 10 && c > MAX_INT % 10))) {
                n = MAX_INT;
                break;
            }
            // 当输入字符串表示数为负数, 且小于MIN_INT
            if (sign < 0 && (n + MIN_INT / 10 > 0 || (n + MIN_INT / 10 == 0 && c + MIN_INT % 10 > 0))) {
                n = MIN_INT;
                break;
            }
            // 把之前得到的数字乘以10, 再加上 当前字符表示的数字
            n = n * 10 + c;
            i++;
        }

        return sign > 0 ? n : -n;
    }

}
