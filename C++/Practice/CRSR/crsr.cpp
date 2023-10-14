#include <iostream>
#include <Windows.h>

int main() {
	POINT crsr;
	while (1) {
		if (GetCursorPos(&crsr)) {
			std::cout << crsr.x << " " << crsr.y << std::endl;
		}
		Sleep(2);
	}
