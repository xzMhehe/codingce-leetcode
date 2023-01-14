package cn.com.codingce.栈队列堆.反转字符串;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseStr(new String("1234")));
    }

    public String reverseStr(String str) {
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            stack.push(c);
        }
        StringBuilder ret = new StringBuilder();
        int k = 0;
        while (!stack.isEmpty()) {
            char c = stack.pop();
            ret.append(c);
            chars[k++] = c;
        }

        //return ret.toString();
        return String.copyValueOf(chars);
    }
}
