package main

import "fmt"

func main() {
	i := 25
	fmt.Printf("tribonacci(i): %v\n", tribonacci(i))
}

/*
输入：n = 4
输出：4
解释：
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
*/
func tribonacci(n int) int {
	if n < 2 {
		return n
	}
	if n == 2 {
		return 1
	}
	p, q, y, r := 0, 0, 1, 1
	for i := 3; i <= n; i++ {
		p = q
		q = y
		y = r
		r = p + q + y
	}
	return r
}
