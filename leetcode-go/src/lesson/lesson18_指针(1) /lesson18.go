package main

import "fmt"

func main() {
	/*
		&：地址操作符，用以得到变量的内存地址
		*：解引用，提供内存地址指向的值
	*/

	answer := 66
	fmt.Println(&answer)

	// address变量实际上是一个*int类型的指针，它可以指向类型为int的其他变量。
	address := &answer
	fmt.Println(*address)

	china := "China"
	var home *string
	fmt.Printf("home is a %T\n", home)

	home = &china
	fmt.Println(*home)

	// home = &answer //error:cannot use &answer (type *int) as type *string in assignment

	// 声明一个string指针
	var administrator *string
	// 指针指向第一个人
	scolese := "Christopher J. Scolese"
	administrator = &scolese
	fmt.Println(*administrator)

	// 指针指向第二个人
	bolden := "Charles F. Bolden"
	administrator = &bolden
	fmt.Println(*administrator) // Charles F. Bolden

	// 修改bolden变量，使用指针访问可以看到变量的更改
	bolden = "Charles Frank Bolden Jr."
	fmt.Println(*administrator)

	// 也可以通过“解引用”间接改变变量
	*administrator = "Maj. Gen. Charles Frank Bolden Jr."
	fmt.Println(bolden) //Maj. Gen. Charles Frank Bolden Jr.

	// 把指针赋值给变量，将会产生一个指向相同变量的指针。
	major := administrator
	*major = "后端码匠"
	fmt.Println(bolden)

	fmt.Println(administrator == major) // true

	// 但是解引用将指针指向的变量赋值给另一个变量将产生一个副本
	charles := *major
	*major = "后端码匠呀"
	fmt.Println(charles)
	fmt.Println(bolden)

	// 就算两个string变量指向不同的地址，但是只要他们的字符串值相同，那么判等时就是ture
	charles = "后端码匠呀"
	fmt.Println(bolden == charles)
	fmt.Println(&bolden == &charles)

	// 指向结构体的指针
	timmy := &person{name: "测试指针结构体", age: 23}
	timmy.superpower = "测试"
	fmt.Printf("%+v", timmy)

	// 指向数组的指针
	superpowers := &[3]string{"flight", "invisibility", "super-strength"}
	fmt.Println(superpowers[1])
	fmt.Println(superpowers[1:3])
}

type person struct {
	name, superpower string
	age              int
}
