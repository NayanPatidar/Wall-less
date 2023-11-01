#include <iostream>
#include <string>
#include <winsock2.h>
#include <Windows.h>
#include <sstream>


#pragma comment(lib, "Ws2_32.lib")  // Linking with Ws2_32.lib

const int port = 8085;

int main() {
	WSAData wsadata;
	POINT crsr;

	if (WSAStartup(MAKEWORD(2, 2), &wsadata) != 0) {
		std::cerr << "WSAData failed with error: " << WSAGetLastError() << std::endl;
		return -1;
	}

	SOCKET sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (sockfd == INVALID_SOCKET) {
		std::cerr << "Socket creation failed with error: " << WSAGetLastError() << std::endl;
		WSACleanup();
		return -1;
	}

	sockaddr_in sockAddr;
	memset(&sockAddr, 0, sizeof(sockAddr));
	sockAddr.sin_family = AF_INET;
	sockAddr.sin_port = htons(port);
	sockAddr.sin_addr.s_addr = inet_addr("10.200.233.107");

	if (sockAddr.sin_addr.s_addr == INADDR_NONE) {
		std::cerr << "inet_addr failed with error: " << WSAGetLastError() << std::endl;
		closesocket(sockfd);
		WSACleanup();
		return -1;
	}

	if (connect(sockfd, reinterpret_cast<sockaddr*>(&sockAddr), sizeof(sockAddr)) == SOCKET_ERROR) {
		std::cerr << "Connection failed with error: " << WSAGetLastError() << std::endl;
		closesocket(sockfd);
		WSACleanup();
		return -1;
	}
	char buffer[1024];

	while (1) {
		memset(buffer, 0, sizeof(buffer));
		int valRead = recv(sockfd, buffer, sizeof(buffer), 0	);

		if (strlen(buffer) == 0) {
			std::cout << "No coordinates from the User !!" << std::endl;
			break;
		}

		std::cout << "Received: " << buffer << std::endl;		

	}

}