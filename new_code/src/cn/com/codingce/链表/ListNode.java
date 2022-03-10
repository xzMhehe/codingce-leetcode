package cn.com.codingce.链表;

public class ListNode {
    public int val;
    public ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
