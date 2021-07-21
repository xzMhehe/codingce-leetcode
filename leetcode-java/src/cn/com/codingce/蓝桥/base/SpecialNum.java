package cn.com.codingce.蓝桥.base;

public class SpecialNum {
	public static void main(String[] args) {
		int a, b, c;
		for (int i = 100; i < 1000; i++) {
			a = i % 10;
			b = i / 10 % 10;
			c = i / 100;
			if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == i) {
				System.out.println(i);
			}
			
		}
	}

}
