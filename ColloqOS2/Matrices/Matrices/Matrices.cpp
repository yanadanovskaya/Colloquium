#include <iostream>
#include <vector>
#include <Windows.h>

const int m = 3; 
const int n = 4; 
const int k = 2; 

int A[m][n] = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

int B[n][k] = {
    {13, 14},
    {15, 16},
    {17, 18},
    {19, 20}
};

int C[m][k] = {};
HANDLE mutex;

DWORD WINAPI ComputeMatrixElement(LPVOID lpParam) {
    int* indices = static_cast<int*>(lpParam);
    int i = indices[0];
    int j = indices[1];

    Sleep(1000);

    int sum = 0;
    for (int x = 0; x < n; x++) {
        sum += A[i][x] * B[x][j];
    }
    C[i][j] = sum;

    DWORD dwWaitResult = WaitForSingleObject(mutex, INFINITE);
    if (dwWaitResult == WAIT_OBJECT_0) {
   
        std::cout << "Thread " << GetCurrentThreadId() << ": Computed C[" << i << "][" << j << "] = " << C[i][j] << std::endl;

        ReleaseMutex(mutex);
    }
    else {
        
        std::cout << "Thread " << GetCurrentThreadId() << ": Failed to acquire mutex." << std::endl;
    }

    return 0;
}

int main() {
    mutex = CreateMutex(NULL, FALSE, NULL);
    std::vector<HANDLE> threads;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < k; j++) {
            int* indices = new int[2];
            indices[0] = i;
            indices[1] = j;
            HANDLE thread = CreateThread(NULL, 0, ComputeMatrixElement, indices, 0, NULL);
            threads.push_back(thread);
        }
    }

    WaitForMultipleObjects(threads.size(), threads.data(), TRUE, INFINITE);

    std::cout << "Resulting matrix C:" << std::endl;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < k; j++) {
            std::cout << C[i][j] << " ";
        }
        std::cout << std::endl;
    }
    // Запись результата в файл-протокол
    // ...

    // Освобождение ресурсов
    CloseHandle(mutex);
    for (HANDLE thread : threads) {
        CloseHandle(thread);
    }

    return 0;
}
