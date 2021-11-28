package main

import "fmt"

func main() {
	str := "abcd"
	fmt.Printf("共计有: %d个", strNum(str))
}

func strNum(str string) int {
	left, right, limit, lenth, result := 0, 1, 0, len(str), 0
	for left < lenth {
		if right >= lenth {
			if limit == 3 {
				result++
			}
			break
		}
		if str[right-1]+1 == str[right] {
			if right-left == 3 {
				result++
				left++
				continue
			}
			if limit < 3 {
				right++
				limit++
			} else {
				limit = 0
				left++
				result++
			}
		} else {
			left++
			right++
		}
	}

	return result
}
