package cn.com.codingce.天理;

/**
 * @description 菱形
 * @author mxz
 */
public class DiamondTest {

    public static void main(String[] args) {
        diamond(6);
    }

    /**
     * @param sides 边数
     */
    public static void diamond(int sides) {
        // 大 正三角
        // i控制层数
        for (int i = 1; i <= sides; i++) {
            // k 控制每行的空格数（空格数是 边数 - 层数）
            for (int k = 1; k <= (sides - i); k++) {
                System.out.print(" ");
            }
            // j 控制每行的星星（星星数是 层数 * 2 - 1）
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // 小 倒三角
        // i 控制层数
        for (int i = 1; i <= sides - 1; i++) {
            // k 控制每行的空格数（空格数 = 边数）
            for (int k = 1; k <= i; k++) {
                System.out.print(" ");
            }
            // j 控制每行的星星（星星数是 (边数 - 层数) - 1）
            for (int j = 1; j <= (sides - i) * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
