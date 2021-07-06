package cn.com.codingce.myarray.tts;

import java.util.Arrays;

/**
 * 274. H 指数
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hIndex(new int[]{1, 8, 9, 10, 11, 1, 2, 3, 5}));

    }

    public int hIndex(int[] citations) {
        // 排序（注意这里是升序排序，因此下面需要倒序扫描）
        Arrays.sort(citations);
        // 线性扫描找出最大的 i
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }

}
