package cn.com.codingce.数组与矩阵.顺时针打印矩阵;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().printMatrix(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        System.out.println(new Solution().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            //上
            for (int i = c1; i <= c2; i++) {
                //第一行 [r1][i]
                ret.add(matrix[r1][i]);
            }
            //右
            for (int i = r1 + 1; i <= r2; i++) {
                ret.add(matrix[i][c2]);
            }

            if (r1 != r2) {
                //下
                for (int i = c2 - 1; i >= c1; i--) {
                    ret.add(matrix[r2][i]);
                }
            }
            if (c1 != c2) {
                //左
                for (int i = r2 - 1; i > r1; i--) {
                    ret.add(matrix[i][r1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ret;
    }

    public int[] spiralOrder(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            //上
            for (int i = c1; i <= c2; i++) {
                //第一行 [r1][i]
                ret.add(matrix[r1][i]);
            }
            //右
            for (int i = r1 + 1; i <= r2; i++) {
                ret.add(matrix[i][c2]);
            }

            if (r1 != r2) {
                //下
                for (int i = c2 - 1; i >= c1; i--) {
                    ret.add(matrix[r2][i]);
                }
            }
            if (c1 != c2) {
                //左
                for (int i = r2 - 1; i > r1; i--) {
                    ret.add(matrix[i][r1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return  ret.stream().mapToInt(Integer::valueOf).toArray();
    }
}
