package cn.com.codingce.栈队列堆.滑动窗口的最大值;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxInWindows(new int[]{1, 2, 5, 9}, 3));
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if(size > num.length || size < 1) {
            return new ArrayList<>();
        }
        // 大顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 维护一个大小为 size 的大顶堆
        for (int i = 0; i < size; i++) {
            priorityQueue.add(num[i]);
        }
        ret.add(priorityQueue.peek());

        for (int i = 0, j = i + size; j < num.length; i++, j ++) {
            priorityQueue.remove(num[i]);
            priorityQueue.add(num[j]);
            ret.add(priorityQueue.peek());
        }
        return ret;
    }

}
