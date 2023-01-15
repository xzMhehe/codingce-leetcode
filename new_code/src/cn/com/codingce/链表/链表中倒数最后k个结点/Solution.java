package cn.com.codingce.链表.链表中倒数最后k个结点;

import cn.com.codingce.链表.ListNode;

public class Solution {
    /**
     * 输入一个链表，输出该链表中倒数第k个结点
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Solution().FindKthToTail(new ListNode(2, new ListNode(3, new ListNode(5, new ListNode(2, null)))), 3));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param pHead ListNode类
     * @param k     int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail(ListNode pHead, int k) {
        if (pHead == null) {
            return null;
        }
        // 快慢指针
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null && k-- > 0) {
            fast = fast.next;
        }
        // k大于链表长度
        if (k > 0) {
            return null;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 时间复杂度：O(2*n)
     * 空间复杂度：O(1)
     *
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail2(ListNode pHead, int k) {
        if (pHead == null || k < 0) return null;
        // 所有节点个数
        int n = 0;
        ListNode cur = pHead;
        while (cur != null) {
            cur = cur.next;
            ++n;
        }
        if (n < k) return null;
        // 正向的位置
        n -= k;
        while (n-- != 0) {
            pHead = pHead.next;
        }

        return pHead;
    }

}
