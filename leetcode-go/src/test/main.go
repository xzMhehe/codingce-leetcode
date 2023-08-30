package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)

func main() {

	test()

}

func test() {
	a := []string{}
	b := []string{}

	// 打开输入文件
	inputFile, err := os.Open("D:\\mxz-code\\gitee\\codingce-leetcode\\leetcode-go\\src\\test\\inputTwo.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer inputFile.Close()

	// 创建带缓冲的读取器
	scanner := bufio.NewScanner(inputFile)

	// 逐行读取输入文件
	one := 0
	for scanner.Scan() {
		line := scanner.Text()
		one++
		b = append(b, fmt.Sprintf("%s-%s", strings.Split(line, "\t")[0], strings.Split(line, "\t")[1]))
	}
	// 创建一个 map 用于存储每个元素出现的次数
	count := make(map[string]int)
	for _, value := range b {
		count[value]++
	}
	///////////////////////////////////////////////////////////////////
	// 打开输入文件
	inputFileTow, err := os.Open("D:\\mxz-code\\gitee\\codingce-leetcode\\leetcode-go\\src\\test\\inputOne.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer inputFileTow.Close()
	// 创建带缓冲的读取器
	scannerTwo := bufio.NewScanner(inputFileTow)
	// 逐行读取输入文件
	two := 0
	for scannerTwo.Scan() {
		line := scannerTwo.Text()
		two++
		a = append(a, fmt.Sprintf("%s-%s", strings.Split(line, "\t")[0], strings.Split(line, "\t")[1]))

	}

	// 计算交集
	intersection := make([]string, 0)
	for _, value := range b {
		for _, val := range a {
			if value == val {
				intersection = append(intersection, value)
				break
			}
		}
	}

	// 计算并集
	union := make([]string, len(a))
	copy(union, a)
	for _, val := range b {
		contains := false
		for _, uVal := range union {
			if val == uVal {
				contains = true
				break
			}
		}
		if !contains {
			union = append(union, val)
		}
	}

	// 计算差集
	difference := make([]string, 0)
	if len(a) > len(b) {
		for _, value := range a {
			contains := false
			for _, val := range b {
				if value == val {
					contains = true
					break
				}
			}
			if !contains {
				difference = append(difference, value)
			}
		}
	} else {
		for _, value := range b {
			contains := false
			for _, val := range a {
				if value == val {
					contains = true
					break
				}
			}
			if !contains {
				difference = append(difference, value)
			}
		}
	}

	//fmt.Println("交集:", intersection)
	fmt.Printf("交集done Size:%d\n", len(intersection))
	fmt.Println("交集:")
	for _, s := range intersection {
		fmt.Printf("'%s',", strings.Split(s, "-")[0])
	}
	//fmt.Println("并集:", union)
	//fmt.Println("差集:")
	//for _, s := range difference {
	//	fmt.Printf("'%s',", strings.Split(s, "-")[0])
	//}
	fmt.Printf("差集done Size:%d\n", len(difference))

	// 遍历 map，输出出现次数大于 1 的元素
	//for key, value := range count {
	//	if value > 1 {
	//		fmt.Printf("%s 出现了 %d 次\n", key, value)
	//	}
	//}

	// 检查扫描器是否出错
	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
