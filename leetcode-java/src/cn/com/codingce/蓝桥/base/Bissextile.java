package cn.com.codingce.蓝桥.base;

import java.util.Scanner;

public class Bissextile {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		if (input % 4 == 0 && input % 100 != 0 || input % 400 == 0) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		sc.close();

	}

}
