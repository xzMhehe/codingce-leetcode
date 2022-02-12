package main

import "fmt"

func main() {

	fmt.Println("选择排序：", selectSort([]int{1, 2, 5, 3, 9, 4, 10}))
}

/*
选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。
唯一的好处可能就是不占用额外的内存空间了吧。
*/
func selectSort(array []int) []int {
	length := len(array)
	for i := 0; i < length; i++ {
		// 1, 2, 5, 3, 9, 4, 10
		// 0  1  2  3  4  5  6
		min := i
		for j := i + 1; j < length; j++ {
			if array[min] > array[j] {
				min = j
			}
		}

		array[i], array[min] = array[min], array[i]
	}

	return array

}
