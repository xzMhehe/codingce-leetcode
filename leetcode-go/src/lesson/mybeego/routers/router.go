package routers

import (
	"mybeego/controllers"

	"github.com/astaxie/beego"
)

func init() {
	beego.Router("/", &controllers.MainController{})
	beego.Router("/hello", &controllers.HelloController{})
	beego.Router("/tologin", &controllers.LoginController{})
}
