package cn.com.codingce.栈队列堆.包含min函数的栈;

import java.util.Stack;

/**
 * @author inke219223m
 * <p>
 * 实现一个包含 min() 函数的栈，该方法返回当前栈中最小的值。
 * <p>
 * <p>
 * <p>
 * Stack.peek()
 * peek()函数返回栈顶的元素，但不弹出该栈顶元素。
 * Stack.pop()
 * pop()函数返回栈顶的元素，并且将该栈顶元素出栈。
 */
public class Solution {
    public static void main(String[] args) {

    }

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.empty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

//力扣
class MinStack {

    Stack<Integer> dataStack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        dataStack.push(x);
        minStack.push(minStack.empty() ? x : Math.min(minStack.peek(), x));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
