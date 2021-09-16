package controllers

import (
	"fmt"

	"github.com/astaxie/beego"
)

type MainController struct {
	beego.Controller
}

func (c *MainController) Get() {
	c.Data["Website"] = "beego.me"
	c.Data["Email"] = "astaxie@gmail.com"

	uid := c.GetSession("uid")
	fmt.Println("uid: ", uid)

	c.TplName = "index.tpl"
}
