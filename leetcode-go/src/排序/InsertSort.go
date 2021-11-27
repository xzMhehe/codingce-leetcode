package main

import "fmt"

func main() {
	fmt.Println("插入排序：", insertSort([]int{1, 2, 5, 3, 9, 4, 10}))
}

/*
插入排序的代码实现虽然没有冒泡排序和选择排序那么简单粗暴，但它的原理应该是最容易理解的了，
因为只要打过扑克牌的人都应该能够秒懂。插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，
对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
插入排序和冒泡排序一样，也有一种优化算法，叫做拆半插入。

*/
func insertSort(array []int) []int {
	// 1, 2, 5, 3, 9, 4, 10
	for i, value := range array {
		perIndex := i - 1
		current := value
		for perIndex > 0 && array[perIndex] > current {
			array[perIndex+1] = array[perIndex]
			perIndex -= 1
		}
		array[perIndex+1] = current
	}

	return array
}
