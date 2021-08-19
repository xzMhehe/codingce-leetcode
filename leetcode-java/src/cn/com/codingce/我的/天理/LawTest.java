package cn.com.codingce.我的.天理;

public class LawTest {
    /**
     * 2.for循环的案例：
     * (1).二者相向而行，从a到b的距离是100，a到b的速度是美妙1米，但是b到a的速度是美妙2米，
     * 案例练习的是for的语法，不是逻辑
     * (2).   1  1    2     3      5       8
     * 1  2   4     7      11     16
     * 规律.....   程序求出第20个数字？
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(test2(5));
        System.out.println(test3(5));
    }


    public static long test2(int n) {
        long a = 1;
        long b = 1;
        if (n == 0) {
            System.out.println("请输入正确数字!");
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            for (int i = 0; i < n - 2; i++) {
                long tem = 0;
                tem = a;
                a += b;
                b = tem;
            }
            return a;
        }
    }

    public static long test3(int n) {
        long result = 1;
        for (int i = 1; i < n - 1; i++) {
            result = 1 + i * (i - 1) / 2;
        }
        return result;
    }
}
