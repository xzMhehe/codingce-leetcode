#include <iostream>

#include <string>

#include <algorithm>

using namespace std;

void show(string str) {
    reverse(str.begin(), str.end()); //str执行完这句，就已经是逆序结果。
    for(char s : str) {
        cout<< s;
    }
}

int main() {

    show("abcd");
    return 0;

}