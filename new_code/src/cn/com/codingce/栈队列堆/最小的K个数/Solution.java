package cn.com.codingce.栈队列堆.最小的K个数;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 最小的K个数
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().GetLeastNumbers_Solution(new int[]{1, 2, 5, 10, 11, 9}, 3));

    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k > input.length || k == 0) {
            return new ArrayList<>();
        }
        //初始化时使用 Lambda 表达式 (o1, o2) -> o2 - o1 来实现大顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : input) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return new ArrayList<>(priorityQueue);
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k > arr.length || k == 0) {
            return new int[]{};
        }
        //初始化时使用 Lambda 表达式 (o1, o2) -> o2 - o1 来实现大顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : arr) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.stream().mapToInt(Integer::valueOf).toArray();
    }
}
