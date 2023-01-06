# 【Java】PriorityQueue梳理

## 简介
PriorityQueue是优先队列的意思。优先队列的作用是能保证每次取出的元素都是队列中权值最小的。这里牵涉到了大小关系，元素大小的评判可以通过元素本身的自然顺序（natural ordering），也可以通过构造时传入的比较器。
- PriorityQueue实现了Queue接口，最大的特点是存取具有优先级，就是
- 根据元素的顺序来决定；
- PriorityQueue是一个无界的容器；
- PriorityQueue底层是基于堆实现的；
- 不允许放入null元素；
- PriorityQueue不是线程安全的；

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202301011803608.png)
继承了AbstractQueue抽象类，实现了Queue接口，具备队列的操作方法
实现了Seriablizable接口，支持序列化

## 构造方法
- PriorityQueue()：构造一个初始容量为11的优先队列；
- PriorityQueue(Comparator<? super E> comparator)：构造一个自定义排器的优先队列；
- PriorityQueue(SortedSet<? extends E> c)：构造一个基于SortedSet内容的优先队列；
## 常用API
- add(E e)：添加元素，如果超过队列长度，抛出异常；
- offer(E e)：添加元素，如果超过队列长度返回false；
- remove()：获取下个元素，如果没有抛出异常；
- poll()：获取下个元素，如果没有返回null；
- element()：查看下个元素的内容，如果没有抛异常；
- peek()：查看下个元素的内容，如果没有返回null；

## example
### 小顶堆
找出前k个最大数，采用小顶堆实现。
```java
package cn.com.codingce.集合容器.PriorityQueue.小顶堆;

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
```



### 大顶堆

要找前k个最小数，则构建大顶堆，要重写compare方法。
```java
package cn.com.codingce.集合容器.PriorityQueue.大顶堆;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindKMin {
    /**
     * 要找前k个最小数，则构建大顶堆，要重写compare方法
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] findKMin(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (pq.peek() > num) {
                // 如果堆顶元素 > 新数，则删除堆顶，加入新数入堆
                pq.poll();
                pq.offer(num);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findKMin(new int[]{2, 1, 3, 5, 7}, 3)));
    }
}
```



## 代码地址

[https://github.com/xzMhehe/codingce-leetcode](https://github.com/xzMhehe/codingce-leetcode)
[https://gitee.com/codingce/codingce-leetcode](https://gitee.com/codingce/codingce-leetcode)