package cn.com.codingce.随做;

/**
 * 83. 删除排序链表中的重复元素
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if(cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}

class ListNode {
    public int val;
    public ListNode next;

}
