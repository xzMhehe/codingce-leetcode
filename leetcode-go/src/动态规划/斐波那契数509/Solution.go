package main

import "fmt"

func main() {
	i := 6
	fmt.Printf("fib(%v): %v\n", i, fib(i))
	fmt.Printf("fib2(%v): %v\n", i, fib2(i))
}

func fib(n int) int {
	if n < 2 {
		return n
	}
	p, q, r := 0, 0, 1
	for i := 2; i <= n; i++ {
		p = q
		q = r
		r = p + q
	}
	return r
}

func fib2(n int) int {
	if n < 2 {
		return n
	}
	return fib2(n-1) + fib2(n-2)
}
