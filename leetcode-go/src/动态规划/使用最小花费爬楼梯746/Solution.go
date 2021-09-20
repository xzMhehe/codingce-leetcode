package main

import "fmt"

func main() {
	cost := [6]int{1, 2, 3, 4, 1, 10}
	fmt.Printf("minCostClimbingStairs(cost[:]): %v\n", minCostClimbingStairs(cost[:]))
	fmt.Printf("minCostClimbingStairs2(cost[:]): %v\n", minCostClimbingStairs2(cost[:]))
}

func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	dp := make([]int, n+1)

	for i := 2; i <= n; i++ {
		dp[i] = min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])
	}
	return dp[n]
}

func minCostClimbingStairs2(cost []int) int {
	n := len(cost)
	pre, cur := 0, 0
	for i := 2; i <= n; i++ {
		pre, cur = cur, min(cur+cost[i-1], pre+cost[i-2])
	}
	return cur
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
