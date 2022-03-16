package cn.com.codingce.链表.删除链表的节点;

import cn.com.codingce.链表.ListNode;

public class Solution {
    public static void main(String[] args) {

    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
                break;
            }
            node = node.next;
        }
        return dummy.next;
    }
}
