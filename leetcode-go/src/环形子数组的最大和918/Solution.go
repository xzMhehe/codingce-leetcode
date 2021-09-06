package main

import "fmt"

/*
918. 环形子数组的最大和

给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。

在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）

此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）

链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray

输入：[1,-2,3,-2]
输出：3
解释：从子数组 [3] 得到最大和 3


输入：[5,-3,5]
输出：10
解释：从子数组 [5,5] 得到最大和 5 + 5 = 10

输入：[3,-1,2,-1]
输出：4
解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
*/
func main() {
	array := []int{1, 10, 3, 0, -1, 2, 7}
	fmt.Println(maxSubarraySumCircular(array))
}

func maxSubarraySumCircular(nums []int) int {
	/*
	   这里解释一下，参照大神的思路。最大和如果不跨环，那么本题退化成数组的子数组的最大和
	   如果跨环，则必然包含nums[0] 与 nums[len(nums)-1]节点，且 nums[1] 到 nums[len(nums)-2] 之间找到一个最小子数组的和。那么整个子数组的和 减去 最小子数组的和 就是最终答案
	*/
	if len(nums) == 1 {
		return nums[0]
	}
	dp := nums[0]
	sum := nums[0] // 整个数组的和
	max := dp      // 子数组的最大和
	for i := 1; i < len(nums); i++ {
		sum += nums[i]
		dp = nums[i] + myMax(dp, 0)
		max = myMax(dp, max)
	}
	min := nums[1]
	dp = nums[0]
	for i := 1; i < len(nums)-1; i++ {
		dp = nums[i] + myMin(dp, 0)
		min = myMin(min, dp)
	}
	return myMax(sum-min, max)
}

func myMax(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func myMin(x, y int) int {
	if x < y {
		return x
	}
	return y
}
