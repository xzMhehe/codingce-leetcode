package cn.com.codingce.栈队列堆.用两个栈实现队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
    }

    //in
    Stack<Integer> stack1 = new Stack<Integer>();
    //out
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() throws Exception {
        if (stack2.isEmpty()) while (!stack1.isEmpty()) stack2.push(stack1.pop());

        if (stack2.isEmpty()) throw new Exception("queue is empty");

        return stack2.pop();
    }
}

class CQueue {
    //力扣
    /**
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
     */
    Deque<Integer> stack3;
    Deque<Integer> stack4;

    public CQueue() {
        stack3 = new LinkedList<Integer>();
        stack4 = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        stack3.push(value);
    }

    public int deleteHead() {
        // 如果第二个栈为空
        if (stack4.isEmpty()) {
            while (!stack3.isEmpty()) {
                stack4.push(stack3.pop());
            }
        }
        if (stack4.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack4.pop();
            return deleteItem;
        }
    }
}
