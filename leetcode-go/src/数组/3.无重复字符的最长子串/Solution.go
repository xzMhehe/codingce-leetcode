package main

import "fmt"

func main() {
	s := "abcabcbb"
	fmt.Printf("最长无重复子串大小: %d", lengthOfLongestSubstring(s))
}

//滑动窗⼝
func lengthOfLongestSubstring(s string) int {
	//限制0
	if len(s) == 0 {
		return 0
	}

	//Ascii完整码表（256个坑位）
	//数组用来标识是否重复
	var freq [256]int
	result, left, right := 0, 0, -1

	for left < len(s) {
		//s := "abcabcbb"
		//-'a' 97, 标识别字母
		//s := "abcabcbb"
		//fmt.Printf("%d", s[3]  ) 97
		if right+1 < len(s) && freq[s[right+1]-'a'] == 0 {
			freq[s[right+1]-'a']++
			right++
		} else {
			freq[s[left]-'a']--
			left++
		}
		result = max(result, right-left+1)
	}
	return result
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
