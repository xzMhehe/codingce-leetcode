package cn.com.codingce.lq.base;

import java.util.Scanner;

public class Palindromic {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a, b, c, d;
		for (int i = 1000; i < 10000; i++) {
			// 1111
			a = i % 10;
			b = i / 10 % 10;
			c = i / 100 % 10;
			d = i / 1000;
			if(a == d && b == c) {
				System.out.println(i);
			}

		}

		sc.close();

	}

}
