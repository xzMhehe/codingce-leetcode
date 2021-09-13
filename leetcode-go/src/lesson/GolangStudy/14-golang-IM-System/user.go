package main

import (
	"fmt"
	"net"
)

type User struct {
	Name string
	Addr string
	C    chan string
	Conn net.Conn
}

// 创建一个用户的API
func NewUser(conn net.Conn) *User {
	userAddr := conn.RemoteAddr().String()
	user := &User{Name: userAddr, Addr: userAddr, C: make(chan string), Conn: conn}
	fmt.Println(user)

	// 启动监听当前user channel消息的groutine
	go user.ListenMessage()

	return user
}

// 监听当前User channel的方法，一旦有消息就直接发送客户端
func (this *User) ListenMessage() {
	for {
		msg := <-this.C
		this.Conn.Write([]byte(msg + "\n"))
	}
}
