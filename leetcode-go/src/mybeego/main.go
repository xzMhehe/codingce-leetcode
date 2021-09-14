package main

import (
	_ "mybeego/routers"

	"github.com/astaxie/beego"
)

func main() {
	beego.Run(":8888") // 默认8080端口
}
