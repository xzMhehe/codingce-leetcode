package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Printf("合并数组: %d", hb([]int{1, 3, 5, 7, 9}, []int{2, 4, 6, 8, 10}))

}

func hb(nums1, nums2 []int) []int {
	var nums []int
	for i := 0; i < len(nums1); i++ {
		nums = append(nums, nums1[i])
	}
	for i := 0; i < len(nums2); i++ {
		nums = append(nums, nums2[i])
	}

	sort.Ints(nums)

	return nums
}
