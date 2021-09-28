package main

import (
	"fmt"
	"sync"
)

func main() {
	newSyncMap()
}

var (
	smp sync.Map
)

func newSyncMap() {
	// fmt.Println("SyncMap测试")

	// // 数据写入
	// smp.Store("name", "小红")
	// smp.Store("age", 18)

	// // 数据读取
	// name, _ := smp.Load("name")
	// fmt.Println(name)

	// age, _ := smp.Load("age")
	// fmt.Println(age)

	go func() {
		for {
			_, _ = smp.Load("1")
		}
	}()

	go func() {
		for {
			smp.Store("1", "2")
			fmt.Print("111")
		}
	}()

	select {}

}

func oldMap() {
	fmt.Println("并发读写Map测试")

	m := make(map[int]int)
	go func() {
		for {
			_ = m[1]
		}
	}()
	go func() {
		for {
			m[2] = 2
		}
	}()
	select {}
	// SyncMap测试
	// fatal error: concurrent map read and map write
}
