package main

import "fmt"

//189. 轮转数组 https://leetcode-cn.com/problems/rotate-array/ 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
func rotate(nums []int, k int) {
	newNums := make([]int, len(nums))
	for i, v := range nums {
		newNums[(i+k)%len(nums)] = v
	}
	copy(nums, newNums)
	fmt.Printf("%v", nums)
}

func main() {
	rotate([]int{-4, -1, 0, 3, 10}, 2)
}
