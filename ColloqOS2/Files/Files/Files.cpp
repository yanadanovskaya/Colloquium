#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <Windows.h>

const int NUM_ACTIONS = 3; // Количество допустимых действий
const std::string INPUT_FILE_PREFIX = "in_";
const std::string OUTPUT_FILE_NAME = "out.dat";

class ActionStrategy {
public:
    virtual double Execute(const std::vector<double>& numbers) = 0;
    virtual ~ActionStrategy() {}
};

class AdditionStrategy : public ActionStrategy {
public:
    double Execute(const std::vector<double>& numbers) {
        double sum = 0.0;
        for (double num : numbers) {
            sum += num;
        }
        return sum;
    }
};

class MultiplicationStrategy : public ActionStrategy {
public:
    double Execute(const std::vector<double>& numbers) {
        double product = 1.0;
        for (double num : numbers) {
            product *= num;
        }
        return product;
    }
};

class SumOfSquaresStrategy : public ActionStrategy {
public:
    double Execute(const std::vector<double>& numbers) {
        double sum = 0.0;
        for (double num : numbers) {
            sum += num * num;
        }
        return sum;
    }
};

DWORD WINAPI ProcessFile(LPVOID lpParam) {
    std::string* filename = static_cast<std::string*>(lpParam);

    std::ifstream inputFile(*filename);
    if (!inputFile.is_open()) {
        std::cerr << "Failed to open file: " << *filename << std::endl;
        return 1;
    }

    int actionCode;
    std::vector<double> numbers;
    std::string line;
    if (std::getline(inputFile, line)) {
        std::istringstream iss(line);
        iss >> actionCode;
    }
    if (std::getline(inputFile, line)) {
        std::istringstream iss(line);
        double num;
        while (iss >> num) {
            numbers.push_back(num);
        }
    }

    ActionStrategy* strategy = nullptr;
    switch (actionCode) {
    case 1:
        strategy = new AdditionStrategy();
        break;
    case 2:
        strategy = new MultiplicationStrategy();
        break;
    case 3:
        strategy = new SumOfSquaresStrategy();
        break;
    default:
        std::cerr << "Invalid action code: " << actionCode << std::endl;
        inputFile.close();
        return 1;
    }

    double result = strategy->Execute(numbers);

    delete strategy;
    inputFile.close();

    std::ofstream outputFile(OUTPUT_FILE_NAME, std::ios::app);
    if (outputFile.is_open()) {
        outputFile << result << std::endl;
        outputFile.close();
    }
    else {
        std::cerr << "Failed to open output file." << std::endl;
        return 1;
    }

    return 0;
}

int main(int argc, char* argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " <directory> <num_threads>" << std::endl;
        return 1;
    }

    std::string directory = argv[1];
    int numThreads = std::stoi(argv[2]);

    std::vector<std::string> inputFiles;
    WIN32_FIND_DATAA findData;
    HANDLE hFind = INVALID_HANDLE_VALUE;
    std::string searchPath = directory + "\\" + INPUT_FILE_PREFIX + "*";
    hFind = FindFirstFileA(searchPath.c_str(), &findData);
    if (hFind != INVALID_HANDLE_VALUE) {
        do {
            if (!(findData.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)) {
                inputFiles.push_back(directory + "\\" + findData.cFileName);
            }
        } while (FindNextFileA(hFind, &findData) != 0);
        FindClose(hFind);
    }
    else {
        std::cerr << "Failed to find input files in the directory: " << directory << std::endl;
        return 1;
    }

    std::vector<HANDLE> threads;
    for (const std::string& inputFile : inputFiles) {
        HANDLE hThread = CreateThread(nullptr, 0, ProcessFile,new std::string(inputFile), 0, nullptr);
        if (hThread == nullptr) {
            std::cerr << "Failed to create thread for file: " << inputFile << std::endl;
            return 1;
        }
        threads.push_back(hThread);
    }

    WaitForMultipleObjects(static_cast<DWORD>(threads.size()), threads.data(), TRUE, INFINITE);

    for (HANDLE hThread : threads) {
        CloseHandle(hThread);
    }

    return 0;
}
