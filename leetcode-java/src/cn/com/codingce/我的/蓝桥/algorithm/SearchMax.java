package cn.com.codingce.我的.蓝桥.algorithm;

import java.util.Scanner;

public class SearchMax {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = scan.nextInt();
        }
        int b = scan.nextInt();
        int[][] brr = new int[b][3];
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < 3; j++) {
                brr[i][j] = scan.nextInt();
            }
        }
        scan.close();
        for (int i = 0; i < b; i++) {
            int c = brr[i][0];
            int d = brr[i][1];
            int e = brr[i][2];
            int z = 0;
            int[] crr = new int[d - c + 1];
            for (int j = c - 1; j <= d - 1; j++) {
                crr[z] = arr[j];
                z++;
            }

            int temp = 0;
            for (int m = 0; m < crr.length - 1; m++) {
                for (int n = 0; n < crr.length - m - 1; n++) {
                    if (crr[n] < crr[n + 1]) {
                        temp = crr[n];
                        crr[n] = crr[n + 1];
                        crr[n + 1] = temp;
                    }
                }
            }
            System.out.println(crr[e - 1]);
        }
    }


}
