#include <iostream>
#include <string>
#include <winsock2.h>

#pragma comment(lib, "Ws2_32.lib")

const int PORT = 8085;
class ConnecBuilder {
public:
	ConnecBuilder(int numConnections) {
		connection(numConnections);
	}
private:
	WSAData wsaData;
	int iResult;
	SOCKET sockfd;
	sockaddr_in sockAddr;

	int connection(int numConnections) {
		iResult = WSAStartup(MAKEWORD(2,2), &wsaData);
		if (iResult < 0) {
			std::cerr << "Failure in Initiating the WSAStartup";
		}

		sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
		if (sockfd == INVALID_SOCKET) {
			std::cerr << "Failure in initiating the SOCKET";
		}

		memset(&sockAddr, 0, sizeof(sockAddr));
		sockAddr.sin_family = AF_INET;
		sockAddr.sin_port = htons(PORT);
		sockAddr.sin_addr.s_addr = inet_addr("10.200.233.107");

		if (sockAddr.sin_addr.s_addr == INADDR_NONE) {
			std::cerr << "inet_addr failed with error: " << WSAGetLastError() << std::endl;
			closesocket(sockfd);
			WSACleanup();
			return 1;
		}

		iResult = connect(sockfd, (sockaddr*)&sockAddr, sizeof(sockAddr));
		if (iResult == SOCKET_ERROR) {
			std::cerr << "Connection failed: " << WSAGetLastError() << std::endl;
			closesocket(sockfd);
			WSACleanup();
			return -1;
		}

		return 1;
			 
	}



	
};