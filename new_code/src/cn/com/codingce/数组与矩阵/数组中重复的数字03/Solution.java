package cn.com.codingce.数组与矩阵.数组中重复的数字03;

public class Solution {
    /**
     * 3. 数组中重复的数字
     * 要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
     *
     * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。在调整过程中，
     * 如果第 i 位置上已经有一个值为 i 的元素，就可以知道 i 值重复。
     *
     * 以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，
     * 因此可以知道 2 重复
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Solution().duplicate(new int[]{2, 3, 1, 0, 2, 5}));
    }

    public int duplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
                // 1 3 2 0 2 5
                // 3 1 2 0 2 5

            }
            swap(nums, i, nums[i]);
            // 0 1 2 3 2 5
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
