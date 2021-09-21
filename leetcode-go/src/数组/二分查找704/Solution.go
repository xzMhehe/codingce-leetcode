package main

import "fmt"

func main() {
	fmt.Printf("search(): %v\n", search([]int{5}, 5))
	fmt.Printf("search2(): %v\n", search2([]int{5}, 5))
}

func search(nums []int, target int) int {
	left, right, mid := 0, len(nums)-1, 0
	for left <= right {
		mid = left + (right-left)>>1
		if nums[mid] == target {
			return mid
		} else if nums[mid] > target {
			right = mid - 1
		} else if nums[mid] < target {
			left = mid + 1
		}
	}
	return -1
}

func search2(nums []int, target int) int {
	left, right, mid := 0, len(nums)-1, 0
	for left <= right {
		mid = left + (right-left)>>1
		if nums[mid] > target {
			right = mid - 1
		} else if nums[mid] == target {
			return mid
		} else if nums[mid] < target {
			left = mid + 1
		}
	}
	return -1
}
