package cn.com.codingce.蓝桥.base;

import java.util.Scanner;

/**
 * 
 * 十六进制转十进制
 * 
 * @author williamma
 *
 */
public class HexadecimalToTen {

	static String[] ten = { "" };

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String num = scn.next();// 输入十六进制
		long sum = 0;// 总数
		for (int i = 0; i < num.length(); i++) {// 循环计算出每位的数相加。
			switch (num.charAt(i)) {
			case '0': {
				sum += 0 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '1': {
				sum += 1 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '2': {
				sum += 2 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '3': {
				sum += 3 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '4': {
				sum += 4 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '5': {
				sum += 5 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '6': {
				sum += 6 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '7': {
				sum += 7 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '8': {
				sum += 8 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case '9': {
				sum += 9 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case 'A': {
				sum += 10 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case 'B': {
				sum += 11 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case 'C': {
				sum += 12 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case 'D': {
				sum += 13 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case 'E': {
				sum += 14 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			case 'F': {
				sum += 15 * Math.pow(16, (num.length() - 1 - i));
				break;
			}
			}
		}
		System.out.println(sum);// 输出
	}

}
