//
// Created by mxz on 2022/12/19.
//

#include<iostream>

using namespace std;

/**
 * 严蔚敏《数据结构》标准分割函数
 *
 * @param A
 * @param low
 * @param high
 * @return
 */
int Paritition1(int A[], int low, int high) {
    int pivot = A[low];
    while (low < high) {
        while (low < high && A[high] >= pivot) {
            --high;
        }
        A[low] = A[high];
        while (low < high && A[low] <= pivot) {
            ++low;
        }
        A[high] = A[low];
    }
    A[low] = pivot;
    return low;
}

void QuickSort(int A[], int low, int high) //快排母函数
{
    if (low < high) {
        int pivot = Paritition1(A, low, high);
        QuickSort(A, low, pivot - 1);
        QuickSort(A, pivot + 1, high);
    }
}

int main() {

    int A[] = {2, 5, 1, 3, 4, 10};
    QuickSort(A, 0, sizeof(A) / sizeof(A[0]));
    for (int num: A) {
        cout << num << " ";
    }

    return 0;
}