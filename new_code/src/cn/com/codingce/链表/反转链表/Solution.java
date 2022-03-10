package cn.com.codingce.链表.反转链表;

import cn.com.codingce.链表.ListNode;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().ReverseList(
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3, null)))).val);
    }

    public ListNode ReverseList(ListNode head) {
        ListNode ret = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = ret;
            ret = cur;
            cur = next;
        }
        return ret;
    }
}
