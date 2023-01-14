package cn.com.codingce.数组与矩阵.字符串转数字;

public class Solution {
    
    public static int Max_INT = Integer.MAX_VALUE;
    public static int Min_INT = Integer.MIN_VALUE;

    public static void main(String[] args) {
        System.out.println(new Solution().getStringToInt("-100"));
    }

    public int getStringToInt(String A) {
        char[] arrayA = A.toCharArray();
        int n = 0;
        if (A.equals(""))     // 判断输入是否为空
            return 0;
        int i = 0;
        while (arrayA[i] == ' ')   // 处理字符串首位的空格
            i++;
        int sign = 1;   // 用于判定输入字符串数字的正负, 初始化为1表示为正数
        if (arrayA[i] == '+' || arrayA[i] == '-') {
            if (arrayA[i] == '-') sign = -1;
            i++;
        }
        while (i < arrayA.length && Character.isDigit(arrayA[i])) {  // 确定是数字0~9才执行循环
            int c = arrayA[i] - '0';
            // 当输入字符串表示数为正数, 且大于Max_INT
            if (sign > 0 && (n > Max_INT / 10 || (n == Max_INT / 10 && c > Max_INT % 10))) {
                n = Max_INT;
                break;
            }
            // 当输入字符串表示数为负数, 且小于Min_INT
            if (sign < 0 && (n + Min_INT / 10 > 0 || (n + Min_INT / 10 == 0 && c + Min_INT % 10 > 0))) {
                n = Min_INT;
                break;
            }
            // 把之前得到的数字乘以10, 再加上 当前字符表示的数字
            n = n * 10 + c;
            i++;
        }

        return sign > 0 ? n : -n;
    }
}
