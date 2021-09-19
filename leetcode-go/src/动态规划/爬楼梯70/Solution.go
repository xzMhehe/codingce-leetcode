package main

import "fmt"

func main() {
	x := 4
	fmt.Printf("climbStairs(x): %v\n", climbStairs(x))
}

/*
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶

*/
func climbStairs(n int) int {
	if n < 2 {
		return n
	}
	p, q, r := 0, 1, 2
	for i := 3; i <= n; i++ {
		p = q
		q = r
		r = p + q
	}
	return r
}
