package main

import "fmt"

type Element interface{}

type List []Element

type Person struct {
	name string
	age  int
}

func (person *Person) String() string {
	return fmt.Sprintf("(name: %s - age %d)", person.name, person.age)
}

func main() {
	list := make(List, 4)
	list[0] = "Hello, 后端码匠"
	list[1] = Person{"天理", 1}
	list[2] = &list[1]
	list[3] = 10010

	for index, element := range list {
		switch value := element.(type) {
		case int:
			fmt.Printf("list[%d] is an int and its value is %d\n", index, value)
		case string:
			fmt.Printf("list[%d] is a string and its value is %s\n", index, value)
		case Person:
			fmt.Printf("list[%d] is a Person and its value is %s\n", index, value.String())
		default:
			fmt.Printf("list[%d] is of a different type (%v)\n", index, value)
		}
	}

}
