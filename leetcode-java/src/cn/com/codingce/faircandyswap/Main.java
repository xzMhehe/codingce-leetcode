package cn.com.codingce.faircandyswap;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(fairCandySwap2(new int[]{1, 2, 5}, new int[]{2, 4})[0] + ", " + fairCandySwap2(new int[]{1, 2, 5}, new int[]{2, 4})[1]);
    }

    public static int[] fairCandySwap(int[] A, int[] B) {
        // 存储A中的元素（换成B也行）
        Set<Integer> set = new HashSet<>();
        int[] result = new int[2];
        int sumA = 0;
        for (int a : A) {
            sumA += a;
            set.add(a);
        }
        int sumB = 0;
        for (int b : B) {
            sumB += b;
        }
        // 两人糖果总量之差的平均数
        int num = (sumA - sumB) / 2;
        // 遍历B中元素，加上差值平均数去另外一个数组里匹配，
        // 能匹配上，表明遇到了需要交换的元素。
        for (int b : B) {
            if (set.contains(b + num)) {
                return new int[]{b + num, b};
            }
        }
        return result;
    }

    public static int[] fairCandySwap2(int[] A, int[] B) {
        int[] result = new int[2];
        boolean[] set = new boolean[100001];
        int sumA = 0;
        for (int a : A) {
            sumA += a;
            set[a] = true;
        }
        int sumB = 0;
        for (int b : B) {
            sumB += b;
        }
        int num = (sumA - sumB) / 2;
        for (int b : B) {
            if (b + num > 0 && b + num < 100001 && set[b + num]) {
                return new int[]{b + num, b};
            }
        }
        return result;
    }
}
