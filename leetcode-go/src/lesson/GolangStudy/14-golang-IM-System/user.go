package main

import (
	"fmt"
	"net"
)

type User struct {
	Name   string
	Addr   string
	C      chan string
	Conn   net.Conn
	server Server
}

// 创建一个用户的API
func NewUser(conn net.Conn, server *Server) *User {
	userAddr := conn.RemoteAddr().String()
	user := &User{Name: userAddr, Addr: userAddr, C: make(chan string), Conn: conn, Server: server}
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

// 上线api
func (this *User) Online() {
	// 当前用户上线，将用户加入到onlineMap中
	this.server.mapLock.Lock()
	this.server.OnlineMap[this.Name] = this
	this.server.mapLock.Unlock()

	// 广播当前上线用户上线消息
	this.server.BroadCast(this, "已上线")
}

// 下线
func (this *User) offline() {
	// 当前用户上线，从map中删除
	this.server.mapLock.Lock()
	delete(this.server.OnlineMap, this.Name)
	this.server.mapLock.Unlock()

	// 广播当前上线用户上线消息
	this.server.BroadCast(this, "下线")
}

// 用户处理消息的请求
func (this *User) DoMessage(msg string) {
	this.server.BroadCast(this, msg)
}
