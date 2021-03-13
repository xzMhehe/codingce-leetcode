package cn.com.codingce.lq.base;

import java.util.Arrays;
import java.util.Scanner;

public class MaxMinSum {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}

		Arrays.sort(array);
		System.out.println(array[n - 1]);
		System.out.println(array[0]);

		int sum = 0;
		for (int i : array) {
			sum += i;
		}
		System.out.println(sum);

	}

}
