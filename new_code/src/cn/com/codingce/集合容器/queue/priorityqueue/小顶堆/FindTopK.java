package cn.com.codingce.集合容器.queue.priorityqueue.小顶堆;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindTopK {
    /**
     * 找出前k个最大数，采用小顶堆实现
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] findKMax(int[] nums, int k) {
        // 队列默认自然顺序排列，小顶堆，不必重写compare
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (pq.peek() < num) {
                // 如果堆顶元素 < 新数，则删除堆顶，加入新数入堆
                pq.poll();
                pq.offer(num);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k&&!pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findKMax(new int[]{2, 5, 1, 3, 9}, 3)));
    }

}
