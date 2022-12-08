//
// Created by mxz on 2022/12/4.
//
#include <iostream>

class Singleton {
private:
    Singleton() {
        std::cout << "constructor called!" << std::endl;
    }

    Singleton(Singleton &) = delete;

    ~Singleton() {
        std::cout << "destructor called!" << std::endl;
    };

    Singleton &operator=(const Singleton &) = delete;

    static Singleton *instance;
public:

    static Singleton *getInstance() {
        if (instance == nullptr) {
            instance = new Singleton;
        }
        return instance;
    }

    void use() { std::cout << "in use" << std::endl; }
};

/// init static member
Singleton *Singleton::instance = nullptr;

int main() {
    Singleton *instance = Singleton::getInstance();
    Singleton *instance_2 = Singleton::getInstance();
    instance->use();
    instance_2->use();
    return 0;
}

