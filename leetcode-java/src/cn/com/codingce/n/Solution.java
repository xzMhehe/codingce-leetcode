package cn.com.codingce.n;

/**
 * 9. 回文数
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * @author mxz
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome2(12121));

    }

    public static boolean isPalindrome(int x) {
        String s = new StringBuffer(x + "").reverse().toString();
        return (x + "").equals(s);
    }

    /**
     * 简单粗暴，看看就行
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        // 转成字符串
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

    /**
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;

        while (x / div >= 10) {
            div *= 10;
            System.out.println("test" + div);
        }
        System.out.println("第一个循环: " + div);

        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static boolean isPalindrome3(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }


}
