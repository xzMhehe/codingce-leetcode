package main

//283. 移动零 https://leetcode-cn.com/problems/move-zeroes/
func moveZeroes(nums []int) {
	var newNums = make([]int, len(nums))
	j := 0
	for _, num := range nums {
		if num != 0 {
			newNums[j] = num
			j++
		}
	}
	for ; j < len(nums); j++ {
		newNums[j] = 0
	}
	copy(nums, newNums)
}

func main() {
	moveZeroes([]int{-4, -1, 0, 3, 10})
}
