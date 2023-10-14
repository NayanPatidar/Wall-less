#include <iostream>
#include <Windows.h>
#include <string.h>

int main() {
	POINT crsr;
	while (1) {
		if (GetCursorPos(&crsr)) {
			//std::cout << crsr.x << " " << crsr.y << std::endl;
			std::string coordiantes = std::to_string(crsr.x) + " " + std::to_string(crsr.y);
			std::cout << coordiantes << std::endl;
		}
		Sleep(2);
	}
}