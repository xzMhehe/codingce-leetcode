package cn.com.codingce.myarray.fft;

/**
 *
 * 453-最小操作次数使数组元素相等
 *
 * 给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。找出让数组所有元素相等的最小操作次数。
 *
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 *
 *
 * @author williamma
 */
public class MySolution {
    public static void main(String[] args) {
        MySolution mySolution = new MySolution();

        // 6     1 * 3（个数）
        System.out.println(mySolution.minMoves(new int[]{1, 2, 3}));


    }
    /**
     * 数学方程
     * <p>
     * 给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。
     * 找出让数组所有元素相等的最小操作次数。
     * <p>
     * int n = nums.length
     * <p>
     * m -> time
     * <p>
     * x 数组中的元素都是x
     * <p>
     * 那么就有等式
     * n * x = sum + m(n - 1) 每次移动 n - 1 次  移动 m 次      sum为过去的和
     * <p>
     * "x = min + m"
     * <p>
     * n(min + m) = sum + m(n - 1) 等式成立
     * <p>
     * <p>
     * 则
     * n * min = sum - m
     * <p>
     * m = sum - n * min
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {

        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        return sum - n * min;
    }

}
