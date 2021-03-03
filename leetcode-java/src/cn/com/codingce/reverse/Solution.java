package cn.com.codingce.reverse;

/**
 * 007 整数反转
 * <p>
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @author mxz
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(reverse3(-153429));
    }

    public static int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            // 每次取末尾数字
            int tmp = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && tmp > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }


    public static int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // 当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            // 当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static int reverse3(int x) {
        int sign = 1;
        if (x < 0) sign = -1;

        x = Math.abs(x);
        int res = 0;

        while (x != 0) {
            int tmp = x % 10 + res * 10;
            if ((tmp - x % 10) / 10 != res) return 0;
            res = tmp;
            x = x / 10;
        }


        return res * sign;

    }

}
