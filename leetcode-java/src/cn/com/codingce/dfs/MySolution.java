package cn.com.codingce.dfs;

import java.util.Scanner;

public class MySolution {
    static int[] marks = new int[20];
    static Scanner sc = new Scanner(System.in);
    static int n = sc.nextInt();
    static int res[] = new int[20];

    static int count = 0;

    public static void main(String[] args) {
        dfs(0);
        System.out.println(count);
    }

    private static void dfs(int step) {
        if (step > n) {
            count++;
            return;
        }
        for (int i = 1; i < n; i++) {
            if (marks[i] == 0) {
                marks[i] = 1;
                res[i] = i;
                dfs(i + 1);
                marks[i] = 0;
            }
        }
    }
}
