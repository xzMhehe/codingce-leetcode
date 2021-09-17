package main

import "github.com/gin-gonic/gin"

func main() {
	// 实例化一个gin
	r := gin.Default()
	r.GET("/ping", sayHello)

	r.GET("/book", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "GET",
		})
	})

	r.POST("/book", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "POST",
		})
	})

	r.PUT("/book", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "PUT",
		})
	})

	r.DELETE("/book", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "DELETE",
		})
	})

	r.Run(":8091") // 监听并在 0.0.0.0:8080 上启动服务
}

func sayHello(c *gin.Context) {
	c.JSON(200, gin.H{
		"message": "pong",
	})
}
