package cn.com.codingce.链表.反转区间节点;

import cn.com.codingce.链表.ListNode;

public class Solution {
    public static void main(String[] args) {

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        for (int i = 1; i < m; i++) {
            p = p.next;
        }

        ListNode pm = p.next;

        for (int i = m; i < n; i++) {
            ListNode temp = pm.next;
            pm.next = temp.next;
            temp.next = p.next;
            p.next = temp;
        }

        return dummy.next;
    }
}
