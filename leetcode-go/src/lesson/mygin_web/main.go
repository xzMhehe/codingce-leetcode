package main

import (
	"fmt"
	"net/http"
)

func sayHello(w http.ResponseWriter, r *http.Request) {
	_, _ = fmt.Fprintln(w, "Hello Golang")
}

func main() {
	fmt.Println("后端码匠")

	http.HandleFunc("/hello", sayHello)

	err := http.ListenAndServe(":9090", nil)
	if err != nil {
		fmt.Println("http serve failed err: ", err)
		return
	}

}
