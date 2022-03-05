package cn.com.codingce.搜索算法.二维数组中的查找;

/**
 * 4. 二维数组中的查找
 *
 * @author inke219223m
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().Find(2, new int[][]{{1, 1}}));
    }

    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int rows = array.length, cols = array[0].length;
        int r = 0, c = cols - 1;
        while(r <= rows - 1 && c >= 0) {
            if(target == array[r][c]) {
                return true;
            } else if(target > array[r][c]) {
                r++;
            } else {
                c--;
            }

        }
        return false;
    }
}
