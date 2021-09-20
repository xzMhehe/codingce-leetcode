package main

func main() {

}

func rob(nums []int) int {
	n, ans := len(nums), 0
	if n == 1 {
		return nums[0]
	}
	if n == 2 {
		return max(nums[0], nums[1])
	}
	if n == 3 {
		return max(max(nums[0], nums[1]), nums[2])
	}
	// 先计算nums[0:n-1], 在计算[1:n]
	a, b, c := nums[0], nums[1], nums[0]+nums[2]
	for i := 3; i < n-1; i++ {
		d := nums[i] + max(a, b)
		a, b, c = b, c, d
	}
	ans = max(b, c)
	a, b, c = nums[n-1], nums[n-2], nums[n-1]+nums[n-3]
	for i := 3; i < n-1; i++ {
		d := nums[n-i-1] + max(a, b)
		a, b, c = b, c, d
	}
	ans = max(ans, max(b, c))

	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
