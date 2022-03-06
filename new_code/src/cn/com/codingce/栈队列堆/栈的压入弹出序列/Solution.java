package cn.com.codingce.栈队列堆.栈的压入弹出序列;

import java.util.Stack;

/**
 * @author inke219223m
 * <p>
 * Stack.peek()
 * peek()函数返回栈顶的元素，但不弹出该栈顶元素。
 * Stack.pop()
 * pop()函数返回栈顶的元素，并且将该栈顶元素出栈。
 */
public class Solution {
    public static void main(String[] args) {

    }

    /**
     * 使用一个栈来模拟压入弹出操作。每次入栈一个元素后，都要判断一下栈顶元素是不是当前出栈序列
     * popSequence 的第一个元素，如果是的话则执行出栈操作并将 popSequence 往后移一位，继续进行判断。
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int n = pushA.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushA[pushIndex]);
            while (popIndex < n && !stack.empty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

}
