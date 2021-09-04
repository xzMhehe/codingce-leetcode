/*
53. Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.
*/

package main

import (
	"fmt"
)

func main() {
	fmt.Print(maxSubArray([]int{1, 2, 3, 4, 6, -1, 0, -2, -1, 9, 10}))
}

func maxSubArray(nums []int) int {
	max := nums[0]
	for i := 1; i < len(nums); i++ {
		if nums[i]+nums[i-1] > nums[i] {
			nums[i] += nums[i-1]
		}
		if nums[i] > max {
			max = nums[i]
		}
	}
	return max
}
