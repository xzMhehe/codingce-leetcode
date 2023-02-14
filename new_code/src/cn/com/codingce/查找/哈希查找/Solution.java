package cn.com.codingce.查找.哈希查找;

/**
 * 哈希查找, 如果所有的键都是整数, 那么就可以使用一个简单的无序数组来实现：将键作为索引,
 * 值即为其对应的值, 这样就可以快速访问任意键的值。
 * 
 * @author mxz
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        HashTableSearch hashTableSearch = solution.new HashTableSearch();
        int[] arr = {1, 3, 5, 65, 6, 34, 67, 343, 56};
        // true
        System.out.println(hashTableSearch.hashSearch(arr, 67));
        // false
        System.out.println(hashTableSearch.hashSearch(arr, 100));
    }

    class HashTableSearch {

        /**
         * 哈希结点
         */
        private class Node {
            int key; // 链表中的键
            Node next; // 下一个同义词
        }

        /***
         * 在哈希表中查找关键字key
         *
         * @param data
         * @param key
         * @return
         */
        public boolean hashSearch(int[] data, int key) {
            int p = 1;
            // 寻找小于或等于最接近表长的素数
            for (int i = data.length; i > 1; i--) {
                if (isPrimes(i)) {
                    p = i;
                    break;
                }
            }
            // 构建哈希表
            Node[] hashtable = createHashTable(data, p);
            // 查找key是否在哈希表中
            int k = key % p;
            Node cur = hashtable[k];
            while (cur != null && cur.key != key) {
                cur = cur.next;
            }
            if (cur == null) {
                return false;
            } else {
                return true;
            }

        }

        /***
         * 用求余, 链表法构建哈希表
         *
         * @param data
         * @param p
         * @return
         */
        public Node[] createHashTable(int[] data, int p) {
            Node[] hashtable = new Node[p];
            // 哈希函数计算的单元地址
            int k;
            for (int i = 0; i < data.length; i++) {
                Node node = new Node();
                node.key = data[i];
                k = data[i] % p;
                if (hashtable[k] == null) {
                    hashtable[k] = node;
                } else {
                    Node current = hashtable[k];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = node;
                }
            }
            return hashtable;
        }

        /***
         * 除余法构建哈希函数, 用链表法解决哈希冲突
         *
         * @param n
         * @return
         */
        public boolean isPrimes(int n) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

}
