package main

import (
	"fmt"
	"sort"
)

//977. 有序数组的平方 https://leetcode-cn.com/problems/squares-of-a-sorted-array/
func sortedSquares(nums []int) []int {
	var list []int
	for _, num := range nums {
		list = append(list, num*num)
	}
	sort.Ints(list)
	return list
}

func main() {
	fmt.Printf("%v\n", sortedSquares([]int{-4, -1, 0, 3, 10}))
}
