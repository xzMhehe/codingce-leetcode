package main

import "fmt"

/*
希尔排序，就是按某个增量值对数据进行分组，每组单独排序好后，再缩小这个增量，然后按新增量对数据分组后每个分组再各自排序。
最终增加缩小到1的时候，排序结束。所以希尔排序又叫缩小增量排序(Diminishing Increment Sort)


最佳增量值的选择其实是个数学难题，有兴趣的可以自己搜下相关资料。
常用的增量有 n/2(这个又叫希尔增量)、n/3、2^k-1(hibbard增量)等，实际使用中稍微改版增量也可能使排序的性能产生很大的波动。
比如使用n/2的增量，就是初始增量就是 length/2 ,第二轮分组时就再除2：length/4，直至增量值变成1

*/
func main() {
	fmt.Println("希尔排序：", Shell2Sort([]int{1, 2, 5, 3, 9, 4, 10}))
}

func shellSort(array []int) []int {
	//首先分组
	length := len(array)
	gap := 1
	for gap < gap/3 {
		gap = gap*3 + 1
	}
	for gap > 0 {
		for i := gap; i < length; i++ {
			temp := array[i]
			j := i - gap
			for j > 0 && array[j] > temp {
				array[j+gap] = array[j]
				j -= gap
			}
			array[j+gap] = temp

		}
		gap = gap / 3
	}
	return array
}

func Shell2Sort(arr []int) []int {
	// 进行分组
	for gap := len(arr) / 2; gap > 0; gap = gap / 2 {
		// 插入排序
		// i 待排序的元素
		for i := gap; i < len(arr); i++ {
			// j 在比较过程中, 待排序元素的位置
			for j := i; j >= gap; j = j - gap {
				// 同组左边的元素 <= 待排序元素
				if arr[j-gap] <= arr[j] {
					break
				}
				// 交换
				arr[j-gap], arr[j] = arr[j], arr[j-gap]
			}
		}
	}
	return arr
}
