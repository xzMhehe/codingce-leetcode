package main

import (
	"fmt"
	"io"
	"net"
	"sync"
)

type Server struct {
	Ip   string
	Port int

	// message管道 在线用户列表
	OnlineMap map[string]*User
	mapLock   sync.RWMutex
	// 消息广播的channel
	Message chan string
}

// 创建一个Server对象
func NewServer(ip string, port int) *Server {
	server := &Server{Ip: ip, Port: port, OnlineMap: make(map[string]*User), Message: make(chan string)}

	return server
}

// 监听Message广播消息channel的goroutine，一旦有消息就发送给在线的user
func (this *Server) ListenMessager() {
	for {
		msg := <-this.Message

		// 将message发送给全部的user
		this.mapLock.Lock()
		for _, cli := range this.OnlineMap {
			cli.C <- msg
		}
		this.mapLock.Unlock()
	}
}

// 广播方法
func (this *Server) BroadCast(user *User, msg string) {
	sendMsg := "[" + user.Addr + "]" + user.Name + ":" + msg
	this.Message <- sendMsg
}

// 处理连接业务
func (this *Server) Handler(conn net.Conn) {
	// fmt.Println("建立连接")
	user := NewUser(conn, this)

	user.Online()

	// 接受客户端传来消息
	go func() {
		buf := make([]byte, 4096)
		for {
			n, err := conn.Read(buf)
			if n == 0 {
				user.offline()
				return
			}
			if err != nil && err != io.EOF {
				fmt.Println("")
			}

			// 提取用户的消息(去除'\n')
			msg := string(buf[:n-1])

			// 将得到的消息进行广播
			// this.BroadCast(user, msg)
			// 针对用户msg处理
			user.DoMessage(msg)
		}
	}()

	// 当前handler阻塞
	select {}

}

// 启动Server服务
func (this *Server) Start() {
	listener, err := net.Listen("tcp", fmt.Sprintf("%s:%d", "127.0.0.1", 8888))
	if err != nil {
		fmt.Println("net.Listen err:", err)
	}
	defer listener.Close()

	// 启动监听Message的goroutine
	go this.ListenMessager()

	for {
		conn, err := listener.Accept()
		if err != nil {
			fmt.Println("listeren accept err:", err)
			continue
		}

		go this.Handler(conn)

	}
}
