package cn.com.codingce.动态规划.斐波那契数列;

/**
 * 求斐波那契数列的第 n 项，n <= 39。
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().Fibonacci2(10000));
    }

    public int Fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int Fibonacci2(int n) {
        if (n <= 2) {
            return 1;
        }
        int pre1 = 1, pre2 = 2, result = 3;
        for (int i = 3; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }
}
