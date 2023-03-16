package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()

	// Simple group: v1
	v1 := router.Group("/v1")
	{
		v1.GET("/goods/list", goodsList)
		v1.POST("/login", loginEndpoint)
		v1.POST("/submit", submitEndpoint)
		v1.POST("/read", readEndpoint)
	}

	// Simple group: v2
	v2 := router.Group("/v2")
	{
		v2.POST("/login", loginEndpoint)
		v2.POST("/submit", submitEndpoint)
		v2.POST("/read", readEndpoint)
	}

	router.Run(":8091")
}
func goodsList(context *gin.Context) {
	context.JSON(http.StatusOK, gin.H{
		"name": "后端码匠",
	})
}

func loginEndpoint(context *gin.Context) {

}

func submitEndpoint(context *gin.Context) {

}
func readEndpoint(context *gin.Context) {

}
