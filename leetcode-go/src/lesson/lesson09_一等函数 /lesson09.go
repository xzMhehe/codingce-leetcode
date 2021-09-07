package main

import (
	"fmt"
	"math/rand"
)

type kelvin float64

func main() {
	fmt.Println("lesson9 First-class functions")

	//【case1】将函数赋值给变量
	sensor := fakeSensor
	fmt.Println(sensor()) //output：156 (随机的)

	sensor = realSensor
	fmt.Println(sensor()) //output：0

	//fmt.Println(sensor) //报错：
}

//返回模拟温度的传感器
func fakeSensor() kelvin {
	return kelvin(rand.Intn(151) + 150)
}

//返回真实温度的传感器
func realSensor() kelvin {
	return 0
}
