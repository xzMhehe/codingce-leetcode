package cn.com.codingce.栈队列堆.反转链表;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reverse(new Node(1, new Node(2, new Node(3, null)))).data);
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


    public Node reverse(Node node) {
        Node prev = null;
        Node now = node;
        while (now != null) {
            //临时，指向断开的节点
            Node next = now.next;
            now.next = prev;
            prev = now;
            now = next;
        }

        return prev;
    }
}


class Node {
    public int data;
    public Node next;

    public Node() {
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}