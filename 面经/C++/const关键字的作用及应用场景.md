# 【C++】const关键字

## 0x1 常量

C++定义常量有两种方式

**#define 宏常量：**`#define 常量名 常量值`

- 通常在文件上方定义，表示一个常量
- 宏常量不可以修改

```cpp
// 宏常量
#define MAX 999

int main() {
	return 0;
}
```

**const修饰的变量：** `const 数据类型 常量名 = 常量值`

- 通常在变量定义前加关键字const，修饰该变量为常量，不可修改
- 常量不可以修改，const限定为只读状态，防止修改操作

```cpp
int main() {
	// const修饰变量
	const int season = 4;
	cout << "一年里总共有 " << season << " 个季节" << endl;
	return 0;
}
```

## 0x2 const修饰指针

const修饰指针有**三种**情况：

- **const修饰指针——常量指针**( `const int *p = &a` )
- **const修饰常量——指针常量**( `int * const p = &a` )
- **const即修饰指针，又修饰常量**(`const int * const p = &a` )

### 0x21 常量指针

常量指针：指针的指向可以修改，但是指针指向的值不可以修改。

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202212282144836.png)

### 0x22 指针常量

指针常量：指针的指向不可以修改，但是指针指向的值可以修改。

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202212282146169.png)

### 0x23 const既修饰指针又修饰常量

特点：指针的指向不可以修改，指针指向的值也不可以修改。

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202212282152120.png)

**总结**

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202212282156688.png)

## 0x3 const修饰结构体

**用const来防止修改操作**

```cpp
//
// Created by mxz on 2022/12/28.
//

#include <iostream>
#include<string>

using namespace std;

// 结构体定义
struct Person {
    string name;  // 姓名
    int age;      // 年龄
};

// 将函数中的形参修改为指针，可以减少内存空间的使用，即不会复制新的副本出来占用空间
void printPerson(const Person *person) // 加const防止函数体中的修改操作
{
    // stu->age = 20;                                    // 操作失败，因为加了const修饰
    cout << "姓名：" << person->name << " 年龄：" << person->age << endl;
}

int main() {
    Person per = {"后端码匠", 18};
    printPerson(&per);

    return 0;
}
```

## 0x4 常量引用

**常量引用主要用来修饰形参，防止形参改变实参**

```cpp
int & ref = 20 // 错误，引用本身需要一个合法的内存空间，20是一个常量

const int & ref = 10// 正确，加上const之后,编译器将代码修改为 int temp = 10; const int & ref = temp;
ref = 18;           // 错误，此时的ref已经是常量，变为只读状态，所以不可以修改

```



```cpp
//
// Created by mxz on 2022/12/28.
//

#include <iostream>

using namespace std;

// 打印数据函数
void showValue(const int &val) {
    //val = 100; // 错误，val现在为只读状态
    cout << "val=" << val << endl;
}

int main() {

    //函数中利用常量引用防止误操作修改实参
    int b = 10;
    showValue(b);

    return 0;
}
```

**总结**

**实际上和const修饰结构体一样，当需要打印数据时，并且当数据量太大，不想复制一份数据到内存空间时，就传一个地址或者引用，此时形参改变，实参也会跟着改变，所以此时需要防止实参被修改！**

## 0x5 const修饰成员函数

### 0x51 常函数

- 成员函数后加const后我们称为这个函数为常函数
- 常函数内不可以修改成员属性
- 成员属性声明时加关键字mutable后，在常函数中依然可以修改

```cpp
//
// Created by mxz on 2022/12/28.
//

#include <iostream>

using namespace std;

class Person {
public:
    // this指针的本质是一个指针常量(Type* const pointer;)，指针的指向不可修改，但是指针指向的值可以修改
    // 如果想让指针指向的值也不可以修改，需要声明常函数，即在成员函数后面加上const

    void ShowPerson() const {

        // m_A = 100;     // 错误，函数加上了const，属性不可以修改
        /**
         * m_A = 100 的本质是 this->m_A = 100，this指针指向的对象的数据是可以修改的
         * this指针的本质是指针常量,this指针的指向不可以修改，即this = NULL; 错误
         * 此处的this指针的本质是指针常量：Person * const this;
        */
        /**
         * 在成员函数后面加const，修饰的是this指针，让this指针指向的值也不可以修改
         * const Person * const this;指针的指向，以及指针指向的值都不可以修改
        */
        this->m_B = 100; // 正确,因为加上了mutable关键字

    }

public:
    int m_A;
    mutable int m_B; // 加上mutable关键字之后成为特殊变量，即使在常函数中，也可以修改这个值
};

void test01() {
    Person person;
    person.ShowPerson();
}

int main() {
    test01();
    return 0;
}
```

### 0x52 常对象

- 声明对象前加const称该对象为常对象
- 常对象只能调用常函数，普通对象既可以调用普通成员函数，也可以调用常函数

```cpp
//
// Created by mxz on 2022/12/28.
//

#include <iostream>

using namespace std;

class Person {
public:
    void ShowPerson() const {
        // m_A = 100; // 错误，函数加上了const，属性不可以修改
        this->m_B = 100; // 正确,因为加上了mutable关键字
    }

    void func() {

    }

public:
    int m_A;
    mutable int m_B; // 加上mutable关键字之后成为特殊变量，即使在常函数中，也可以修改这个值
};

void test02() {
    const Person p = {1, 2}; // 在对象前加const，变为常对象
    // p.m_A = 100;        // 错误，常对象不允许修改成员属性
    p.m_B = 100;            // 正确，m_B是特殊值，在常对象下也可以修改

    //常对象只能调用常函数
    p.ShowPerson();          // 正确
    // p.func();             // 错误，因为普通成员函数可以修改成员属性，而常对象的本质是不允许修改成员属性
}

int main() {
    test02();
    return 0;
}
```

