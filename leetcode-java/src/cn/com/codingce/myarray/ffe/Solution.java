package cn.com.codingce.myarray.ffe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 *
 * @author williamma
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println(new Solution().findDisappearedNumbersTwo(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    /**
     * 原地哈希
     * <p>
     * 1 2 3 4 3 2 7 8
     *
     * 1-n之间
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //4, 3, 2, 7, 8, 2, 3, 1
        int len = nums.length;
        int index = 0;
        while (index < len ) {
            if (nums[index] == index + 1) {
                index++;
            } else {
                int targetIndex = nums[index] - 1;
                //若目标数等于下标数则不需要进行转换, 进入下一个数
                if (nums[targetIndex] == nums[index]) {
                    index++;
                    continue;
                }
                //若否则进行换数
                int temp = nums[targetIndex];
                nums[targetIndex] = nums[index];
                nums[index] = temp;
            }

        }

        List<Integer> res = new ArrayList<>();

        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }

        return res;
    }

    /**
     *
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbersTwo(int[] nums) {
        int[] array = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            array[nums[i]] = 1;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                list.add(i);
            }
        }

        return list;
    }
}
