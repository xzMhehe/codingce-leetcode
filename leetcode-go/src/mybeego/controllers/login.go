package controllers

import (
	"fmt"

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
	c.Data["name"] = c.GetString("username")
	c.Data["pass"] = c.GetString("pass")
	fmt.Println("测试")
	c.Ctx.WriteString("userLogin")
}
