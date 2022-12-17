# C++中unordered_map梳理

## 0x1 简介

unordered_map 容器，直译过来就是"无序 map 容器"的意思。所谓“无序”，指的是 unordered_map 容器不会像 map 容器那样对存储的数据进行排序。换句话说，unordered_map 容器和 map 容器仅有一点不同，即 map 容器中存储的数据是有序的，而 unordered_map 容器中是无序的。
**头文件**

```cpp
#include<unordered_map>
```

## 0x2 unordered_map和map的区别

### 0x21 **内部实现机理不同**

**map** :map内部实现了一个`红黑树`（红黑树是非严格平衡二叉搜索树，而AVL是严格平衡二叉搜索树），红黑树具有自动排序的功能，因此map内部的所有元素都是有序的，红黑树的每一个节点都代表着map的一个元素。因此，对于map进行的查找、删除，添加等一系列的操作都相当于是对红黑树进行的操作。map中的元素是按照二叉搜索树 （又名儿茶查找树、二叉排序树–特点就是左子树上所有节点的键值都小于根节点的键值，右子树所有节点的键值都大于根结点的键值）存储的，使用中序遍历可将键值按照从小到大遍历出来。

**unordered_map** :unordered_map内部实现了一个`哈希表` （也叫`散列表`，通过把关键码值映射到Hash表中一个位置来访问记录，查找的时间复杂度可达到`O(1)`，其在`海量数据处理中`有着广泛应用）。因此，其元素的排列顺序都是无序的。哈表的概念见：详谈哈希表。

### 0x22 **优缺点**

**map优点**：有序性，这是map结构最大的有点，其元素的有序性在很多应用中都会简化很多的操作；红黑树，内部实现一个红黑树使得map的很多操作在lgn的时间复杂度下就可以实现，因此效率非常的高。

**map缺点**：空间占用率高，因为map内部实现了红黑树，虽然提高了运行效率，但是因为每一个节点都需要额外保存父节点、孩子节点和红/黑性质，使得每一个节点都占用大量的空间。

**适用**：对于那些有顺序要求的问题，用map会更高效一些。

**unordered_map优点**：因为内部实现了哈希表，因此其查找速度非常的快。

**unordered_map缺点**：哈希表的建立比较耗费时间

**适用**：对于查找问题，**unordered_map** 会更加高效一些，因此遇到查找问题，常会考虑一下用**unordered_map**。

## C++ unordered_map容器的成员方法

- begin()：返回指向容器中第一个键值对的正向迭代器。auto it=mymap.begin()

- end()：返回指向容器中最后一个键值对之后位置的正向迭代器。	
- cbegin()：和 begin() 功能相同，只不过在其基础上增加了 const 属性，即该方法返回的迭代器不能用于修改容器内存储的键值对。
- cend()：和 end() 功能相同，只不过在其基础上，增加了 const 属性，即该方法返回的迭代器不能用于修改容器内存储的键值对。
- empty() ：若容器为空，则返回 true；否则 false。
- size()：返回当前容器中存有键值对的个数。
- max_size()：返回容器所能容纳键值对的最大个数，不同的操作系统，其返回值亦不相同。
- operator[key]：该模板类中重载了 [] 运算符，其功能是可以向访问数组中元素那样，只要给定某个键值对的键 key，就可以获取该键对应的值。注意，如果当前容器中没有以 key 为键的键值对，则其会使用该键向当前容器中插入一个新键值对。
- at(key)：返回容器中存储的键 key 对应的值，如果 key 不存在，则会抛出 out_of_range 异常。
- find(key)：查找以 key 为键的键值对，如果找到，则返回一个指向该键值对的正向迭代器；反之，则返回一个指向容器中最后一个键值对之后位置的迭代器（如 end() 方法返回的迭代器）。
- count(key)：在容器中查找以 key 键的键值对的个数。
- equal_range(key)：返回一个 pair 对象，其包含 2 个迭代器，用于表明当前容器中键为 key 的键值对所在的范围。
- emplace()：向容器中添加新键值对，效率比 insert() 方法高。
- emplace_hint()：向容器中添加新键值对，效率比 insert() 方法高。
- insert()：向容器中添加新键值对。
- erase()：删除指定键值对。
- clear()：清空容器，即删除容器中存储的所有键值对。
- swap()：交换 2 个 unordered_map 容器存储的键值对，前提是必须保证这 2 个容器的类型完全相等。
- reserve()：将存储桶的数量（也就是 bucket_count() 方法的返回值）设置为至少容纳count个元（不超过最大负载因子）所需的数量，并重新整理容器。



## Example

```cpp
//
// Created by mxz on 2022/12/16.
//
#include<iostream>
#include<unordered_map>
#include<map>

using namespace std;

int main() {
    // 注意：c++11才开始支持括号初始化
    unordered_map<int, string> myMap = {{5, "后端码匠"},
                                        {6, "欢迎关注"}};  // 使用{}赋值
    myMap[2] = "code";  // 使用[ ] 进行当个插入，若已存在键值2，则赋值修改，若无则插之。
    myMap.insert(pair<int, string>(3, "代码"));  // 使用insert和pair插入。

    // 遍历输出+迭代器的使用。
    auto iter = myMap.begin();  // auto自动识别为迭代器类型unordered_map<int, string>::iterator
    while (iter != myMap.end()) {
        cout << iter->first << "," << iter->second << endl;
        ++iter;
    }

    // 查找元素并输出 + 迭代器的使用
    auto iterator = myMap.find(2);  // find()返回一个指向2的迭代器。
    int i = 0;
    if (iterator != myMap.end()) {
        cout << endl << i << iterator->first << "," << iterator->second << endl;
        ++i;
    }

    return 0;
}
```

