package controllers

import (
	"fmt"
	"mybeego/models"

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
	username := c.GetString("username")
	password := c.GetString("pass")
	result := make(map[string]interface{})

	user, err := models.FindUsersByUserName(username)
	fmt.Println("user:", user)
	if err != nil {
		result["code"] = 204
		fmt.Println("查询用户失败", err)
		return
	}

	if password != user.Password {
		result["code"] = 204
		fmt.Println("密码错误")
		return
	}

	c.SetSession("user", user)
	c.SetSession("uid", user.Id)

	fmt.Println("完成")
	result["code"] = 200
	result["data"] = user

	c.Data["json"] = result

	user2 := c.GetSession("user")
	fmt.Println("user2:", user2)

	c.ServeJSON()
}
