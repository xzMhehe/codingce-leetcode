package main

import "github.com/gin-gonic/gin"

func main() {
	// 实例化一个gin
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	r.Run(":8083") // 监听并在 0.0.0.0:8080 上启动服务
}
