#include <iostream>
#include <Windows.h>
#include <string>
#include <winsock2.h>

const int port = 8085;
int main() {
	WSAData wsaData;

	if (WSAStartup(MAKEWORD(2, 2), &wsaData)) {
		std::cerr << "Got Error while using windows WSAData Library !";
		return -1;
	}

	SOCKET sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (sockfd == INVALID_SOCKET) {
		std::cerr << "Socket Creation failed !";
	}

	sockaddr_in socketAdrr;
	memset(&socketAdrr, 0, sizeof(socketAdrr));
	socketAdrr.sin_family = AF_INET;
	socketAdrr.sin_port = htons(port);
	socketAdrr.sin_addr.s_addr = inet_addr("10.200.233.107");
	if (serverAddr.sin_addr.s_addr == INADDR_NONE) {
		std::cerr << "inet_addr failed with error: " << WSAGetLastError() << std::endl;
		closesocket(sockfd);
		WSACleanup();
		return 1;
	}

	if (connect(&sockfd, (socketAdrr*)&sockfd, sizeof(socketAdrr))) {
		std::cerr << "Connection failed: " << WSAGetLastError() << std::endl;
		closesocket(sockfd);
		WSACleanup();
		return -1;
	}




	

}
