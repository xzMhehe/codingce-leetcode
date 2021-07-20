package cn.com.codingce.mlist.on;


/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *
 *
 * @author williamma
 */
public class Solution {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(5, new ListNode(9))));
        System.out.println(new Solution().removeNthFromEnd(listNode, 3));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }


    /**
     *
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 慢指针初始指向虚拟头结点
        ListNode slow = dummyHead;
        // 快指针初始指向虚拟头结点
        ListNode fast = dummyHead;

        // 快指针先向前移动n+1步
        for(int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 快慢指针同时向前移动，直到快指针指向null
        while (fast!=null){
            fast = fast.next;
            slow = slow.next;
        }

        // 慢指针的下一个节点即待删除节点
        ListNode delNode = slow.next;
        // 慢指针的后继指针指向待删除节点的下一个节点
        // 这样就将待删除节点删除了
        slow.next = delNode.next;
        delNode.next = null;
        return dummyHead.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
