package cn.com.codingce.查找.插值查找;

/**
 * 插值查找, 基于二分查找算法, 将查找点的选择改进为自适应选择, 可以提高查找效率.它是二分查找的改进版.
 *
 * @author mxz
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().insertionSearch(new int[]{2, 5, 6, 8, 10}, 8, 0, 4));
    }


    /**
     * 插值查找, 递归, 适合表长较大, 而关键字分布又比较均匀的查找表.
     *
     * @param array
     * @param target
     * @param l
     * @param r
     * @return
     */
    public int insertionSearch(int[] array, int target, int l, int r) {
        if (l > r) {
            return -1;
        }
        // 唯一与二分查找的区别点
        int mid = l + (target - array[l]) / (array[r] - array[l]) * (r - l);
        if (array[mid] == target)
            return mid;
        if (array[mid] > target)
            return insertionSearch(array, target, l, mid - 1);
        if (array[mid] < target)
            return insertionSearch(array, target, mid + 1, r);
        return -1;
    }

}
