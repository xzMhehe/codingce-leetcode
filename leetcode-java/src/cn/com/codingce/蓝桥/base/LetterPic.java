package cn.com.codingce.蓝桥.base;

import java.util.Scanner;

public class LetterPic {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();

		int[][] array = new int[a][b];
		for (int i = 0, n = a; i < n; i++) {
			for (int j = 0; j < b; j++) {
				System.out.print((char) ('A' + Math.abs((i - j))));
			}
			System.out.println();

		}
		
		sc.close();
	}
}
