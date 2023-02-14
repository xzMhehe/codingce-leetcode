package cn.com.codingce.查找.斐波那契查找;

/**
 * 斐波那契查找, 在是二分查找的一种提升算法, 通过运用黄金比例的概念在数列中选择查找点进行查找, 提高查找效率.
 * 注意同时属于一种有序查找算法
 *
 * @author mxz
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().fibSearch(new int[]{2, 4, 6, 8, 10}, 4, 8));
    }

    /**
     * 斐波那契数列, 采用递归
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 斐波那契数列, 采用动态规划
     *
     * @param n
     * @return
     */
    public static int fibT(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int pre1 = 0, pre2 = 1, result = 1;
        for (int i = 3; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    /**
     * 斐波那契查找
     *
     * @param array  目标数组
     * @param n      数组长度 - 1
     * @param target 目标数
     * @return 数组下标
     */
    public int fibSearch(int[] array, int n, int target) {
        // 记录从1开始
        int low = 1;
        // high不用等于fib(k)-1，效果相同
        int high = n;
        int mid;

        int k = 0;
        while (n > fibT(k) - 1)
            k++;
        // 因为无法直接对原数组array[]增加长度，所以定义一个新的数组
        int[] temp = new int[fibT(k)];
        // 采用System.arrayaycopy()进行数组间的赋值
        System.arraycopy(array, 0, temp, 0, array.length);
        // 对数组中新增的位置进行赋值
        for (int i = n + 1; i <= fibT(k) - 1; i++)
            temp[i] = temp[n];

        while (low <= high) {
            mid = low + fibT(k - 1) - 1;
            if (temp[mid] > target) {
                high = mid - 1;
                // 对应上图中的左段，长度F[k-1]-1
                k = k - 1;
            } else if (temp[mid] < target) {
                low = mid + 1;
                // 对应上图中的右端，长度F[k-2]-1
                k = k - 2;
            } else {
                if (mid <= n)
                    return mid;
                else
                    // 当mid位于新增的数组中时，返回n
                    return n;
            }
        }
        return 0;
    }

}
