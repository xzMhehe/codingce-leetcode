package cn.com.codingce.ste;

public class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.maximumProduct(new int[]{1, 2, 3, 4}));
        System.out.println(solution.maximumProduct(new int[]{1000, 1000, 1000}));
        System.out.println(solution.maximumProduct(new int[]{-1000, -1000, -1000}));



    }

    public int maximumProduct(int[] nums) {
        // 最小的和第二小的
        int minOne = Integer.MAX_VALUE, minTwo = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        //获取三个最大的
        int maxOne = Integer.MIN_VALUE; // 第一大的
        int maxTwo = Integer.MIN_VALUE; // 第二大的
        int maxThree = Integer.MIN_VALUE; // 第三大的

        //遍历数组
        for (int num : nums) {
            //插入位置确定
            if (num < minOne) {
                minTwo = minOne;
                minOne = num;
            } else if (num < minTwo) {
                minTwo = num;
            }

            //插入位置确定
            if (num > maxOne) {
                maxThree = maxTwo;
                maxTwo = maxOne;
                maxOne = num;
            } else if (num > maxTwo) {
                maxThree = maxTwo;
                maxTwo = num;
            } else if (num > maxThree) {
                maxThree = num;
            }
        }
        return Math.max(minOne * minTwo * maxOne, maxOne * maxTwo * maxThree);
    }
}
