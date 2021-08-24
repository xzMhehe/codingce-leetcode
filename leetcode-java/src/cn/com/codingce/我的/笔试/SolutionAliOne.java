package cn.com.codingce.我的.笔试;

import java.util.Stack;

/**
 * 题目: 给定一个整数数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 例:
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
class SolutionAliOne {

    public static void main(String[] args) {
        System.out.println(new SolutionAliOne().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(new SolutionAliOne().isValid("{[]}"));
    }

    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    public boolean isValid2(String s) {
        //这里定义了一个栈，栈是矢量的一个子类，它实现了一个标准的先进后出的栈
        Stack<Character> stack = new Stack<Character>();
        //将字符串转成char数组，循环遍历
        for (char c : s.toCharArray()) {
            //将数组取出的值与对应的括号比较，如果右括号存在把对应左括号放入栈中，
            // 如果出现的是左括号，栈中不会存在值，直接返回
            //stack.push 把值放入到栈的最顶部
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                //stack.pop()取栈中最顶部的值
                return false;
            }
        }
        return stack.isEmpty();
    }

}


