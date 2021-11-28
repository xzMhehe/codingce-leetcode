package main

import "fmt"

func main() {
	fmt.Printf("下标为: %d", nums([]int{17, 29, 56, 3, 9, 11}))
}

func nums(nums []int) int {
	for index, value := range nums {
		if value > nums[index+1] {
			return index + 1
		}
	}

	return 0
}
