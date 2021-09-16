package main

import "github.com/gin-gonic/gin"

func main() {
	// Disable Console Color
	// gin.DisableConsoleColor()

	// 使用默认中间件创建一个gin路由器
	// logger and recovery (crash-free) 中间件
	router := gin.Default()

	router.GET("/someGet", getting)
	router.POST("/somePost", posting)
	router.PUT("/somePut", putting)
	router.DELETE("/someDelete", deleting)
	router.PATCH("/somePatch", patching)
	router.HEAD("/someHead", head)
	router.OPTIONS("/someOptions", options)

	// 默认启动的是 8080端口，也可以自己定义启动端口
	router.Run()
	// router.Run(":3000") for a hard coded port
}

func getting(context *gin.Context) {

}
func posting(context *gin.Context) {

}
func putting(context *gin.Context) {

}
func deleting(context *gin.Context) {

}
func patching(context *gin.Context) {

}
func head(context *gin.Context) {

}
func options(context *gin.Context) {

}
