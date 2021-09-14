package main

import (
	_ "mybeego/routers"

	"github.com/astaxie/beego"
)

func main() {

	// beego.SetStaticPath("/down1", "download1")
	// beego.SetStaticPath('/down2', "download2")
	beego.Run(":8888") // 默认8080端口
}
