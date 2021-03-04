package cn.com.codingce.tjise;

public class Multi1030 {
    static boolean flag[] = new boolean[10];
    static int test[] = new int[10];

    public static void main(String[] args) {
        dfs(1);
    }

    static void dfs(int step) {
        if (step == 10 && ((test[1] * 100 + test[2] * 10 + test[3]) + (test[4] * 100 + test[5] * 10 + test[6]) == (test[7] * 100 + test[8] * 10 + test[9]))) {
            System.out.println((test[1] * 100 + test[2] * 10 + test[3]) + "+" + (test[4] * 100 + test[5] * 10 + test[6]) + "=" + (test[7] * 100 + test[8] * 10 + test[9]));

            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (flag[i] == false) {  //没有被访问过
                flag[i] = true;
                test[step] = i;
                dfs(step + 1);
                flag[i] = false;
            }
        }
    }
}
