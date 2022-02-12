package main

import "fmt"

func main() {

	fmt.Printf("%d\n", searchInsert([]int{1, 3, 5, 6}, 7))
}

func searchInsert(nums []int, target int) int {
	n := len(nums)
	left, right := 0, n-1
	ann := n
	for left <= right {
		mid := left + (right-left)>>1
		if nums[mid] >= target {
			ann = mid
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	return ann
}
