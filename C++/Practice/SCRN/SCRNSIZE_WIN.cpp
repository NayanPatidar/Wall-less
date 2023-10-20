#include <iostream>
#include <Windows.h>

int main() {
	int screenWidth = GetSystemMetrics(SM_CXSCREEN);
	int screenHeight = GetSystemMetrics(SM_CYSCREEN);
	std::cout << screenWidth << " " << screenHeight;
}