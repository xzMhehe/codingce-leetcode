package main

import "fmt"

func main() {
	fmt.Println("SyncMap测试")
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
