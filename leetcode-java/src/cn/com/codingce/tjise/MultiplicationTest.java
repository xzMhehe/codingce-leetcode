package cn.com.codingce.tjise;

/**
 * @author mxz
 * @description 九九乘法表
 */
public class MultiplicationTest {

    public static void main(String[] args) {
        multiplication();
        System.out.println();
        multiplication2();
    }

    public static void multiplication() {
        for (int i = 9; i >= 1; i--) {
            for (int j = 9; j >= i; j--) {
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println(" ");
        }
    }


    public static void multiplication2() {
        for (int i = 9; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println(" ");
        }
    }


}
