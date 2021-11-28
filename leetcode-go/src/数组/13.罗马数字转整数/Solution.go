package main

import "fmt"

/*
罗⻢数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

通常情况下，罗⻢数字中⼩的数字在⼤的数字的右边。但也存在特例，例如 4 不写做 IIII，⽽是 IV。数 字 1 在数字 5 的左边，
所表示的数等于⼤数 5 减⼩数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这 个特殊的规则只适⽤于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

给定⼀个罗⻢数字，将其转换成整数。输⼊确保在 1 到 3999 的范围内。

链接：https://leetcode-cn.com/problems/roman-to-integer
*/
func main() {
	fmt.Printf("罗马数字转整数: %d\n", romanToInt("MIX"))

}

var roman = map[string]int{
	"I": 1,
	"V": 5,
	"X": 10,
	"L": 50,
	"C": 100,
	"D": 500,
	"M": 1000,
}

func romanToInt(s string) int {
	if s == "" {
		return 0
	}
	num, lastInt, total := 0, 0, 0
	for i := 0; i < len(s); i++ {
		char := s[len(s)-(i+1) : len(s)-i]
		num = roman[char]
		if num < lastInt {
			total = total - num
		} else {
			total = total + num
		}
		lastInt = num
	}
	return total
}
