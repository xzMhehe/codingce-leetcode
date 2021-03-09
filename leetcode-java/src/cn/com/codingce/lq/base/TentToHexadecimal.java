package cn.com.codingce.lq.base;

import java.util.Scanner;

/**
 * 十进制转十六进制
 *
 * @author williamma
 */
public class TentToHexadecimal {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();
		System.out.println(tentToHexadecimal(input));
		sc.close();
	}

	private static String tentToHexadecimal(int input) {
		if (input == 0) {
			return "0";
		}

		StringBuffer stringBuffer = new StringBuffer();

		while (input > 0) {
			switch (input % 16) {
			case 10:
				stringBuffer.append("A");
				break;
			case 11:
				stringBuffer.append("B");
				break;
			case 12:
				stringBuffer.append("C");
				break;
			case 13:
				stringBuffer.append("D");
				break;
			case 14:
				stringBuffer.append("E");
				break;
			case 15:
				stringBuffer.append("F");
				break;
			default:
				stringBuffer.append(input % 16);

			}
			input /= 16;

		}

		return stringBuffer.reverse().toString();
	}

}
