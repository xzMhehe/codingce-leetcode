package main

import "fmt"

//557. 反转字符串中的单词 III
func reverseWords(s string) string {
	length := len(s)
	var ret []byte
	for i := 0; i < length; {
		start := i
		for i < length && s[i] != ' ' {
			i++
		}
		for p := start; p < i; p++ {
			ret = append(ret, s[start+i-1-p])
		}
		for i < length && s[i] == ' ' {
			i++
			ret = append(ret, ' ')
		}
	}
	return string(ret)
}

func main() {
	fmt.Printf(reverseWords("Let's take LeetCode contest"))
}
