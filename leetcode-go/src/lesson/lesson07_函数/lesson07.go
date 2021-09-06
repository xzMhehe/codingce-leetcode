package main

import "fmt"

func main() {
	fmt.Println("lesson07 函数")
	value1 := 290.1
	value2 := kelvinToCelsius(value1)

	fmt.Println(value1, "°K is", value2, "°C")

	sum := addAll(3, 4, 5, 6, 7)
	fmt.Println(sum)
}

// kelvinToCelsius converts °K to °C
func kelvinToCelsius(k float64) float64 {
	k -= 273.15
	return k
}

// addAll 多数相加
func addAll(a int, numbers ...int) int {
	sum := a
	for _, v := range numbers {
		sum += v
	}
	return sum
}
