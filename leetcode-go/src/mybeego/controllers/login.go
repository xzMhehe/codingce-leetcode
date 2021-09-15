package controllers

import (
	"github.com/astaxie/beego"
)

type LoginController struct {
	beego.Controller
}

func (c *LoginController) Get() {
	c.Data["Website"] = "codingce.com.cn"
	c.Data["Email"] = "codingce@gmail.com"
	c.TplName = "login.tpl"
}
func (c *LoginController) Post() {
	c.Data["name"] = c.GetString("name")
	c.Data["pass"] = c.GetString("pass")
	c.TplName = "index.tpl"
}
