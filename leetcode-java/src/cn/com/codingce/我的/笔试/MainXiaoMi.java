package cn.com.codingce.我的.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数组A，以及数组A元素数量
 * <p>
 * 数组B，以及数组B元素数量
 * <p>
 * A = [1,6,7,0,0,0], m = 3
 * <p>
 * B = [2,4,6], n = 3
 * <p>
 * 样例输入
 * m=2,n=2
 * 1,3
 * 2,4
 * <p>
 * <p>
 * 样例输出
 * 1 2 3 4
 *
 * @author maxinze
 */
public class MainXiaoMi {
    public static void main(String[] args) {
        new MainXiaoMi().nums();
    }

    public void nums() {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split(",");
        int m = Integer.parseInt(split[0].substring(2));
        int n = Integer.parseInt(split[1].substring(2));
        String[] splitA = sc.next().split(",");
        String[] splitB = sc.next().split(",");
        int len = m + n;
        int[] result = new int[len];

        int i = 0;
        for (; i < m; i++) {
            result[i] = Integer.parseInt(splitA[i]);
        }
        for (int j = 0; i < len; i++, j++) {
            result[i] = Integer.parseInt(splitB[j]);
        }

        Arrays.sort(result);
        for (int item : result) {
            System.out.print(item + " ");
        }
    }
}
