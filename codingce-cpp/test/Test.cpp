//
// Created by mxz on 2022/12/28.
//

#include<iostream>

using namespace std;

class A {
public:
    void foo() { printf("1\n"); }

    virtual void fun() { printf("2\n"); }
};

class B : public A {
public:
    void foo() { printf("3\n"); }

    void fun() {
        printf("4");
    };
};

int main() {
    A a;
    B b;
    A *p = &a;
    p->foo();
    p->fun();
    p = &b;
    p->foo();
    p->fun();
    return 0;
}