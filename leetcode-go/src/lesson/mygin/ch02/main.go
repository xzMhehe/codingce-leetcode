package main

import "github.com/gin-gonic/gin"

func main() {
	router := gin.Default()

	router.GET("/someGet")
	router.POST("/somePost")
	router.PUT("/somePut")
	router.DELETE("/someDelete")
	router.PATCH("/somePatch")
	router.HEAD("/someHead")
	router.OPTIONS("/someOptions")

	router.Run(":8091")
}
