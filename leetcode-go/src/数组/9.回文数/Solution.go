package main

import (
	"fmt"
	"strconv"
)

func main() {
	fmt.Printf("是否是回文数: %t", isPalindrome(123))

}

func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	if x < 10 {
		return true
	}
	s := strconv.Itoa(x)
	lens := len(s)
	for i := 0; i < lens; i++ {
		if s[i] != s[lens-1-i] {
			return false
		}
	}
	return true
}
