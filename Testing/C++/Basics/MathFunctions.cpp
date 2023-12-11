#include <iostream>
#include "MathFunctions.h"

int add (int a, int b){
    return a+b;
}

int subtract(int a, int b){
    return a-b;
}

double multiply(double a, double b){
    return a*b;
}

double division(double a, double b){
    return a/b;
}

int main() {
    double a, b;
    a = 5;
    b = 5;
    std::cout << division(a, b) << "\n\n";
    return 0;
}
