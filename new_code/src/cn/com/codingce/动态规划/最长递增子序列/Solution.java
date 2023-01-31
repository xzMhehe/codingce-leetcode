package cn.com.codingce.动态规划.最长递增子序列;

/**
 * 给定数组arr，设长度为n，输出arr的最长递增子序列。（如果有多个答案，请输出其中字典序最小的）
 *
 * @author mxz
 */
public class Solution {
    public static void main(String[] args) {

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * retrun the longest increasing subsequence
     *
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     */
    public int[] LIS(int[] arr) {
        // write code here
        int len = 1, n = arr.length;
        if (n == 0) {
            return new int[0];
        }
        int[] d = new int[n + 1];
        int[] w = new int[n];
        d[len] = arr[0];
        w[0] = len;
        for (int i = 1; i < n; ++i) {
            if (arr[i] > d[len]) {
                d[++len] = arr[i];
                w[i] = len;
            } else {
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < arr[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = arr[i];
                w[i] = pos + 1;
            }
        }
        int[] res = new int[len];
        for (int i = n - 1, j = len; j > 0; --i) {
            if (w[i] == j) {
                res[--j] = arr[i];
            }
        }
        return res;
    }
}
