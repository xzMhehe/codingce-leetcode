package cn.com.codingce.链表.链表中环的入口结点;

import cn.com.codingce.链表.ListNode;

/**
 * 链表中环的入口结点
 *
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 *
 * @author inke219223m
 */
public class Solution {
    public static void main(String[] args) {

    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        //边界
        if (pHead == null) {
            return null;
        }
        ListNode low = null;
        ListNode fast = null;
        while (pHead != null) {
            pHead = pHead.next;
        }

        return fast;
    }
}
