package cn.com.codingce.数组与矩阵.打印从1到最大的n位数;

import java.util.ArrayList;

/**
 *
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，
 * 则打印出 1、2、3 一直到最大的 3 位数 999。
 * 1. 用返回一个整数列表来代替打印
 * 2. n 为正整数，0 < n <= 5
 */
public class Main {

    public static void main(String[] args) {

        System.out.println(printNumbers(3));

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n int整型 最大位数
     * @return int整型一维数组
     */
    public static int[] printNumbers(int n) {
        // 1、定义一个list用于存放需要打印的数
        ArrayList<Integer> list = new ArrayList<>();
        // 2、设置初始值为1
        int count = 1;
        /*
           3、String.valueOf 把 count 转为字符串
                并判断它的长度是否小于等于n
                比如：n=1， 5.length()<1   true  进入循环
                           10.length()   false  推出循环
        */
        while ((String.valueOf(count).length() <= n)) {
            // 先把count 添加到list中
            list.add(count);
            count++;
        }

        // 4、定义返回的数组
        int[] res = new int[list.size()];
        // 5、遍历list，添加到数组中
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}