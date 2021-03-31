package cn.com.codingce.lq.realquestion;

/**
 * 如【图1.png】的五星图案节点填上数字：1~12，除去7和11。
 * 要求每条直线上数字和相等。
 * <p>
 * 如图就是恰当的填法。
 * <p>
 * 请你利用计算机搜索所有可能的填法有多少种。
 * 注意：旋转或镜像后相同的算同一种填法。
 * <p>
 * 请提交表示方案数目的整数，不要填写任何其它内容。
 *
 * @author williamma
 */
public class for6_2 {
    static int[] mark = new int[20];   // 标记是否使用
    static int[] res = new int[20];    // 记录排列数组
    static int len = 10;
    static int count = 0;   // 计数

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        mark[7] = 1;
        mark[11] = 1;

        dfs(0);
        System.out.println(count / 10);
    }

    public static void dfs(int n) {
        if (len == n) {
            int a1 = res[1] + res[2] + res[3] + res[4];
            int a2 = res[0] + res[2] + res[5] + res[8];
            int a3 = res[0] + res[3] + res[6] + res[9];
            int a4 = res[1] + res[5] + res[7] + res[9];
            int a5 = res[4] + res[6] + res[7] + res[8];
            if (a1 == a2 && a1 == a3 && a1 == a4 && a1 == a5)
                count++;

            return;
        }
        for (int i = 1; i <= 12; i++) {
            if (mark[i] == 0)//没有使用过
            {
                mark[i] = 1;
                res[n] = i;
                dfs(n + 1);
                mark[i] = 0;
            }
        }
    }
}
