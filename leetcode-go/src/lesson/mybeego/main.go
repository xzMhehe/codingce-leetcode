package main

import (
	_ "mybeego/routers"

	beego "github.com/astaxie/beego"

	"github.com/astaxie/beego/context"
)

func main() {
	beego.BConfig.WebConfig.Session.SessionOn = true // 开启session会话数据
	// beego.SetStaticPath("/down1", "download1")
	// beego.SetStaticPath('/down2', "download2")
	beego.Run(":8888") // 默认8080端口

	beego.InsertFilter("/*", beego.BeforeRouter, LoginFiler)
}

var LoginFiler = func(ctx *context.Context) {
	_, ok := ctx.Input.Session("uid").(int)
	if !ok && ctx.Request.RequestURI != "/tologin" {
		ctx.Redirect(302, "/tologin")
	}
}
