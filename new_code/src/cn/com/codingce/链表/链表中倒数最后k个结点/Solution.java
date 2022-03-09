package cn.com.codingce.链表.链表中倒数最后k个结点;

public class Solution {
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

}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
