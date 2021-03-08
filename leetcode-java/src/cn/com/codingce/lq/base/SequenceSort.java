package cn.com.codingce.lq.base;

import java.util.Arrays;
import java.util.Scanner;

public class SequenceSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = scanner.nextInt();
        }

        Arrays.sort(nums);
        for (int i : nums){
            System.out.print(i + " ");
        }
    }

    public static void test() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        for (int k : a) {
            System.out.print(k + " ");
        }
    }
}
