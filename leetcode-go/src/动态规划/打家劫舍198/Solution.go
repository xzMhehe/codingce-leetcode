package main

import "fmt"

func main() {
	nums := []int{2, 1}
	fmt.Printf("rob(nums): %v\n", rob(nums))
}

func rob(nums []int) int {
	n := len(nums)
	if n == 0 {
		return 0
	}
	if n == 1 {
		return nums[0]
	}
	dp := make([]int, n)
	dp[0] = nums[0]
	dp[1] = max(nums[0], nums[1])
	for i := 2; i < n; i++ {
		dp[i] = max(dp[i-2]+nums[i], dp[i-1])
	}
	return dp[n-1]
}

func rob3(nums []int) int {
	first, second := nums[0], max(nums[0], nums[1])
	for _, v := range nums[2:] {
		first, second = second, max(first+v, second)
	}
	return second
}

func rob2(nums []int) int {
	n := len(nums)
	if n == 0 {
		return 0
	}
	if n == 1 {
		return nums[0]
	}
	first := nums[0]
	second := max(nums[0], nums[1])
	for i := 2; i < n; i++ {
		first, second = second, max(first+nums[i], second)
	}
	return second
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
