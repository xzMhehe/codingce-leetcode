package controllers

import (
	"fmt"

	"github.com/astaxie/beego"
)

type HelloController struct {
	beego.Controller
}

func (c *HelloController) Get() {
	c.Data["Website"] = "codingce.com.cn"
	c.Data["Email"] = "codingce@gmail.com"
	c.TplName = "hello.tpl"
}
func (c *HelloController) Post() {
	// 接受post请求
	c.Ctx.WriteString("post 的提交方式 codingce codingce")
	// 接受请求变量
	name := c.GetString("name")
	pass := c.GetString("pass")
	fmt.Println("你的账号：" + name + "你的密码：" + pass)
	c.Ctx.WriteString("你的账号：" + name + "你的密码：" + pass)
}
