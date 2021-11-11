package main

import (
	"fmt"

	"github.com/go-redis/redis/v7"
)

var (
	client = redis.NewClient(&redis.Options{
		Addr:     "localhost:6379",
		Password: "", // no password set
		DB:       0,  // use default DB
	})
)

func main() {
	IsExist("mykey", "haha")
	SetKeyValue("mykey", "user1", "李雷")
	GetKeyValue("mykey", "user1")
}

func IsExist(db string, key string) {
	// 判断 key 是否存在
	val, err := client.Get(fmt.Sprintf("%s:%s", db, key)).Result()
	if err == redis.Nil {
		fmt.Println(key, " does not exist")
	} else if err != nil {
		panic(err)
	} else {
		fmt.Println("读取：", val)
	}
}

func SetKeyValue(db string, key string, value string) {
	fmt.Println("---SetKey---")
	err := client.Set(db+":"+key, value, 0).Err()
	if err != nil {
		fmt.Println(err)
		panic(err)
	}
	fmt.Println("SETDONE")
}

func GetKeyValue(db string, key string) {
	fmt.Println("---GetKey---")
	val2, err := client.Get(db + ":" + key).Result()
	if err != nil {
		fmt.Println(err)
		panic(err)
	}
	fmt.Println("读取", val2)
}
