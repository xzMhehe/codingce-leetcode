package main

import (
	"fmt"
	"path/filepath"
	"sync"
	"time"

	"github.com/BurntSushi/toml"
)

var (
	cfg  *tomlConfig
	once sync.Once
)

func Config() *tomlConfig {
	once.Do(func() {
		filePath, err := filepath.Abs("/Users/inke219223m/mxz-code/codingce-leetcode/leetcode-go/src/my/my_toml/config.toml")
		if err != nil {
			panic(err)
		}
		fmt.Printf("parse toml file once. filePath: %s\n", filePath)
		if _, err := toml.DecodeFile(filePath, &cfg); err != nil {
			panic(err)
		}
	})
	return cfg
}

func main() {
	var config tomlConfig
	filePath := "/Users/inke219223m/mxz-code/codingce-leetcode/leetcode-go/src/my/my_toml/config.toml"
	if _, err := toml.DecodeFile(filePath, &config); err != nil {
		panic(err)
	}

	// 配置中DB的IP
	fmt.Println(Config().DB.Server)
	// 配置中Owner的名字
	fmt.Println(Config().Owner.Name)
}

type tomlConfig struct {
	Title   string
	Owner   ownerInfo
	DB      database `toml:"database"`
	Servers map[string]server
	Clients clients
}

type ownerInfo struct {
	Name string
	Org  string `toml:"organization"`
	Bio  string
	DOB  time.Time
}

type database struct {
	Server  string
	Ports   []int
	ConnMax int `toml:"connection_max"`
	Enabled bool
}

type server struct {
	IP string
	DC string
}

type clients struct {
	Data  [][]interface{}
	Hosts []string
}
