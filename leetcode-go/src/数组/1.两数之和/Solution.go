package main

import "fmt"

func main() {
	nums := []int{1, 2, 5, 7, 9, 10}
	target := 7
	fmt.Printf("两数之和: %+v, target: %d\n", twoSum(nums, target), target)
}

func twoSum(nums []int, target int) []int {
	m := make(map[int]int)
	for i := 0; i < len(nums); i++ {
		another := target - nums[i]
		//判断是否在 map 中
		if _, ok := m[another]; ok {
			return []int{m[another], i}
		}
		//不在就添加到 map
		m[nums[i]] = i
	}

	return nil
}
