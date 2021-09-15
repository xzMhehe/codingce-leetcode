package models

import (
	"fmt"
	"testing"
)

func TestFindUsersByUserName(t *testing.T) {
	fmt.Println("进入测试")
	user, err := FindUsersByUserName("admin")
	if err != nil {
		fmt.Println("查询失败")
	}
	fmt.Println("查询成功")

	fmt.Println(user)
}

func TestAllUser(t *testing.T) {
	users, err := AllUsers()

	fmt.Println("users:", users)
	fmt.Println("err:", err)

}
