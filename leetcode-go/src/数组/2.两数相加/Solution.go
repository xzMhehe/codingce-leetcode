package main

import "fmt"

func main() {
	l3 := ListNode{3, nil}

	l2 := ListNode{Val: 4, Next: &l3}

	l1 := ListNode{Val: 2, Next: &l2}

	l6 := ListNode{Val: 4, Next: nil}

	l5 := ListNode{Val: 6, Next: &l6}

	l4 := ListNode{Val: 5, Next: &l5}

	// 输入：l1 = [2,4,3], l2 = [5,6,4]
	// 输出：[7,0,8]
	// 解释：342 + 465 = 807.
	fmt.Printf("两数相加: %+v", addTwoNumbers(&l1, &l4))
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil && l2 == nil {
		return nil
	}
	head := &ListNode{0, nil}
	current := head
	//进位
	carry := 0
	//l1 = [2,4,3], l2 = [5,6,4]
	//FOR_START
	for l1 != nil || l2 != nil {
		var x, y int
		if l1 == nil {
			x = 0
		} else {
			x = l1.Val
		}
		if l2 == nil {
			y = 0
		} else {
			y = l2.Val
		}
		// 这里有点绕哈, 给 current 的 Next 赋值
		// % 是求余数, 不是整除 /, 比如 4 + 6 + 0 = 10, 10 % 10 = 0 符合; 10 / 10 = 1 显然不符合相加规范
		current.Next = &ListNode{(x + y + carry) % 10, nil}
		// 让 current == 刚才赋值的 Next
		current = current.Next
		carry = (x + y + carry) / 10
		if l1 != nil {
			l1 = l1.Next
		}
		if l2 != nil {
			l2 = l2.Next
		}
	}
	//FOR_EDN
	//其实是逆序的708, 7是顶, 8是底
	if carry > 0 {
		current.Next = &ListNode{carry % 10, nil}
	}
	return head.Next
}

type ListNode struct {
	Val  int
	Next *ListNode
}
