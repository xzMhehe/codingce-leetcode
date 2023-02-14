package cn.com.codingce.查找.分块查找;

import java.util.ArrayList;
import java.util.List;

/**
 * 分块查找
 * <p>
 * 它是顺序查找的一种改进方法. 将 n 个数据元素 "按块有序" 划分为 m 块（m ≤ n）.
 * 每一块中的结点不必有序, 但块与块之间必须"按块有序"；
 * 第 i 块中的每个元素一定比第 i-1 块中的任意元素大（小）.
 *
 * @author mxz
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 索引表
        int[] index = new int[]{10, 20, 30};
        BlockSearch blockSearch = solution.new BlockSearch(index);
        blockSearch.insert(-1);
        blockSearch.insert(10);
        blockSearch.insert(25);
        // 查找
        blockSearch.search(0);
        blockSearch.search(-1);
        blockSearch.search(10);
        blockSearch.search(25);
    }

    class BlockSearch {

        private int[] index;
        private List<List<Integer>> list;

        // 创建索引表
        public BlockSearch(int[] index) {
            if (index != null && index.length != 0) {
                this.index = index;
                list = new ArrayList<>();
                for (int i = 0; i < index.length; i++) {
                    list.add(new ArrayList<>());
                }
            } else {
                throw new Error("index cannot be null or empty.");
            }
        }

        // 对插入数据进行分区
        public void insert(int data) {
            int i = binarySearch(data);
            list.get(i).add(data);
        }

        // 结合二分查找
        public void search(int data) {
            int i = binarySearch(data);
            for (int j = 0; j < list.get(i).size(); j++) {
                if (data == list.get(i).get(j)) {
                    System.out.printf("'%d' Position: [%d,%d]%n", data, i, j);
                    return;
                }
            }
            System.out.printf("'%d' Position: Not found%n", data);
        }

        // 二分查找
        private int binarySearch(int data) {
            if (data > index[index.length - 1]) {
                throw new Error("out of block range");
            }

            int start = 0;
            int end = index.length - 1;
            int mid;
            while (start < end) {
                mid = (start + end) / 2;
                if (index[mid] > data) {
                    end = mid - 1;
                } else {
                    // 如果相等, 也插入后面 <=index[start]
                    start = mid + 1;
                }
            }
            return start;
        }
    }

}
