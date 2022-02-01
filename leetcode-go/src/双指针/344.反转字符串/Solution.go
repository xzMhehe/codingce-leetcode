package main

import "fmt"

//344. 反转字符串
func reverseString(s []byte) {
	for left, right := 0, len(s)-1; left <= right; left++ {
		s[left], s[right] = s[right], s[left]
		right--
	}
	fmt.Printf("%s", s)

}

func main() {
	reverseString([]byte{'a', 'b', 'c', 'd'})
}
