package cn.com.codingce.foo;

/**
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * one = 47 two = 45 three = 32
 *
 *
 *
 * @author williamma
 */
public class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.thirdMaxTwo(new int[]{3,2,1}));
    }

    public int thirdMax(int[] nums) {
        //三个数存放
        long one = Long.MIN_VALUE;
        long two = Long.MIN_VALUE;
        long three = Long.MIN_VALUE;

        //遍历数组
        for (int num : nums) {
            //注意数组重复问题
            if (num == one || num == two || num <= three) {
                continue;
            }
            //插入位置确定
            if (num > one) {
                three = two;
                two = one;
                one = num;
            } else if (num > two && num < one) {
                three = two;
                two = num;
            } else if(num < two) {
                three = num;
            }

        }

        //判断three是不是变过
        if(three == Long.MIN_VALUE) {
            //没变
            return (int) one;
        } else  {
            return (int) three;
        }
    }

    public int thirdMaxTwo(int[] nums) {
        //第一个是三个数中最大的
        long one = Long.MIN_VALUE;
        long two = Long.MIN_VALUE;
        //三个数中最小的
        long three = Long.MIN_VALUE;

        for(int num : nums) {
            if(num == one || num == two || num < three) {
                continue;
            }
            //插入位置确定
            if(num > one) {
                three = two;
                two = one;
                one = num;
            } else if(num > two && num < one) {
                three = two;
                two = num;
            } else if(num < two) {
                three = num;
            }
        }
        if(three == Long.MIN_VALUE) {
            return (int)one;
        }
        return (int)three;
    }

}
