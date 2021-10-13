package main

import "fmt"

func main() {
	nums := []int{2, 3, 4}
	sum := 0
	for _, num := range nums {
		sum += num
	}
	fmt.Println("Sum:", sum)

	// 在数组上使用range将传入index和值两个变量。上面那个例子我们不需要使用该元素的序号，所以我们使用空白符"_"省略了。
	// 有时侯我们确实需要知道它的索引。
	for index, num := range nums {
		if num == 3 {
			fmt.Println("Index: ", index)
		}
	}

	kvs := map[string]string{"a": "apple", "b": "huawei"}
	for key, value := range kvs {
		fmt.Println("key: ", key, "value: ", value)
	}

	// range也可以用来枚举Unicode字符串。第一个参数是字符的索引，第二个是字符（Unicode的值）本身。
	for i, c := range "go" {
		fmt.Println(i, c)
	}

}
