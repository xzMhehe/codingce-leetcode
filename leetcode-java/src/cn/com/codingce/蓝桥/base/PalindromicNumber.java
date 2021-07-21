package cn.com.codingce.蓝桥.base;

import java.util.Scanner;

public class PalindromicNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a, b, c, d, e, f;
		for (int i = 10000; i <= 99999; i++) {	// 五位数回文数
			a = i % 10;// 个位
			b = i / 10 % 10;// 十位
			c = i / 100 % 10;// 百位
			d = i / 1000 % 10;// 千位
			e = i / 10000;// 万位
			if (a == e && b == d && a + b + c + d + e == n) {
				System.out.println(i);
			}
		}
		for (int i = 100000; i < 999999; i++) {	// 六位数回文数
			a = i % 10;// 个位
			b = i / 10 % 10;// 十位
			c = i / 100 % 10;// 百位
			d = i / 1000 % 10;// 千位
			e = i / 10000 % 10;// 万位
			f = i / 100000;
			if (a == f && b == e && c == d && a + b + c + d + e + f == n) {
				System.out.println(i);
			}
		}
		sc.close();

	}

}
