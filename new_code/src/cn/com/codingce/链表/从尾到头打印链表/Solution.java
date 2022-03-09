package cn.com.codingce.链表.从尾到头打印链表;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().printListFromTailToHead2(
                new ListNode(1, new ListNode(2, new ListNode(3, null)))));
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (listNode != null) {
            //递归原素listNode.next
            ret.addAll(printListFromTailToHead(listNode.next));
            ret.add(listNode.val);
        }
        return ret;
    }

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ListNode ret = null;
        ListNode curNode = listNode;
        while (curNode != null) {
            ListNode tmp = curNode.next;
            curNode.next = ret;
            ret = curNode;
            curNode = tmp;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (ret != null) {
            list.add(ret.val);
            ret = ret.next;
        }
        return list;
    }
}


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


