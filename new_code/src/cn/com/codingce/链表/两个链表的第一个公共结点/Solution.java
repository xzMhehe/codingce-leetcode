package cn.com.codingce.链表.两个链表的第一个公共结点;

import cn.com.codingce.链表.ListNode;

/**
 * 两个链表的第一个公共结点
 * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 *
 * @author inke219223m
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().FindFirstCommonNode(null, null);
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null ? pHead2 : l1.next);
            l2 = (l2 == null ? pHead1 : l2.next);
        }
        return l1;
    }
}
