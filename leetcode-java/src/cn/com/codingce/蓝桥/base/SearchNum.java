package cn.com.codingce.蓝桥.base;

import java.util.Scanner;

public class SearchNum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}
		int target = sc.nextInt();
		int j = 0;
		for (; j < array.length; j++) {
			if (array[j] == target) {
				System.out.println(j + 1);
				break;
			}
		}
		if (j == n) {
			System.out.println(-1);
		}
		
		sc.close();

	}

}
