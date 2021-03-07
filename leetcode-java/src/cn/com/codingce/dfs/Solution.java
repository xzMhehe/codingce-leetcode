package cn.com.codingce.dfs;

import java.util.Scanner;

/**
 * @author williamma
 */
public class Solution {
    static Scanner sc = new Scanner(System.in);
    static int n = sc.nextInt();
    static int result[] = new int[n + 1];
    static int vis[] = new int[n + 1];

    public static void main(String[] args) {
        DFS(1);

    }

    private static void DFS(int step) {
        if (step >= n + 1) {
            print();
            return;
        }
        // 关键就是这里的理解 核心思想就是用标记数组来保证dfs的走位，也可以自行在颅内编译一下，值得注意的是for循环在当前函数正在执行的次数，搞清楚此即可.
        // 值得注意的是，标记的数组标记的是目前已经使用了哪些数字（或者说具体的全排列元素
        // for循环遍历目前哪个元素还未被使用进行填充
        // for循环相当于搜索 赋值为0操作相当于回溯
        for (int j = 1; j <= n; j++) {
            if (vis[j] == 0) {
                vis[j] = 1;//标记数组
                result[step] = j;
                DFS(step + 1);
                vis[j] = 0;//回溯
            }
        }
    }

    private static void print() {
        //对应问题3
        for (int i = 1; i <= n; i++) {
            System.out.printf("%5d", result[i]);
        }
        System.out.println();
    }

    public int dfs(int x) {
        if (x == 1) {
            return 1;
        }
        return x + dfs(x - 1);
    }


}
