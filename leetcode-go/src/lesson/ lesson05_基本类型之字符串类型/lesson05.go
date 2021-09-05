package main

import "fmt"

func main() {

	// 字符串类型
	str := "pace"
	fmt.Println(str)

	// 使用`` , 可以方便的定义跨行字符串
	fmt.Println(`
peace be upon you
upon you be peace
	`)
	// rune和byte类型
	var pi rune = 960
	var alpha rune = 940
	var omega rune = 969
	var bang byte = 33

	fmt.Printf("%v %v %v %v\n", pi, alpha, omega, bang) //960 940 969 33

}
