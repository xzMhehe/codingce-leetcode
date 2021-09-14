package controllers

import (
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
