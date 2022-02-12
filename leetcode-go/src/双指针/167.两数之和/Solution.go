package main

import "fmt"

//167. 两数之和 II - 输入有序数组
func twoSum(numbers []int, target int) []int {
	for i := 0; i < len(numbers); i++ {
		low, high := i+1, len(numbers)-1
		for low <= high {
			mid := (high-low)>>1 + low
			if numbers[mid] == target-numbers[i] {
				return []int{i + 1, mid + 1}
			} else if numbers[mid] > target-numbers[i] {
				high = mid - 1
			} else {
				low = mid + 1
			}
		}
	}
	return []int{-1, -1}
}

func twoSum1(numbers []int, target int) []int {
	for i := 0; i < len(numbers); i++ {
		left, right := i+1, len(numbers)-1
		for left <= right {
			mid := (right-left)>>1 + left
			if numbers[mid] == target-numbers[i] {
				return []int{i + 1, mid + 1}
			} else if numbers[mid] > target-numbers[i] {
				right = mid - 1
			} else {
				left = mid + 1
			}
		}
	}
	return []int{-1, -1}
}

func main() {
	fmt.Printf("%v\n", twoSum1([]int{4, 1, 0, 3, 10}, 13))
}
