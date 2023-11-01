//#include <iostream>
//#include <string>
//#include <winsock2.h>
//#include <Windows.h>
//#include <sstream>
//
//
//#pragma comment(lib, "Ws2_32.lib")  // Linking with Ws2_32.lib
//
//const int port = 8085;
//
//int main() {
//	WSAData wsadata;
//	POINT crsr;
//
//	if (WSAStartup(MAKEWORD(2, 2), &wsadata) != 0) {
//		std::cerr << "WSAData failed with error: " << WSAGetLastError() << std::endl;
//		return -1;
//	}
//
//	SOCKET sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
//	if (sockfd == INVALID_SOCKET) {
//		std::cerr << "Socket creation failed with error: " << WSAGetLastError() << std::endl;
//		WSACleanup();
//		return -1;
//	}
//
//	sockaddr_in sockAddr;
//	memset(&sockAddr, 0, sizeof(sockAddr));
//	sockAddr.sin_family = AF_INET;
//	sockAddr.sin_port = htons(port);
//	sockAddr.sin_addr.s_addr = inet_addr("10.200.233.107");
//
//	if (sockAddr.sin_addr.s_addr == INADDR_NONE) {
//		std::cerr << "inet_addr failed with error: " << WSAGetLastError() << std::endl;
//		closesocket(sockfd);
//		WSACleanup();
//		return -1;
//	}
//
//	if (connect(sockfd, reinterpret_cast<sockaddr*>(&sockAddr), sizeof(sockAddr)) == SOCKET_ERROR) {
//		std::cerr << "Connection failed with error: " << WSAGetLastError() << std::endl;
//		closesocket(sockfd);
//		WSACleanup();
//		return -1;
//	}
//	char buffer[1024];
//	std::string accumulatedData;
//
//	fd_set readSet;
//
//	while (1) {
//		FD_ZERO(&readSet);
//		FD_SET(sockfd, &readSet);
//
//		struct timeval timeout;
//		timeout.tv_sec = 0;
//		timeout.tv_usec = 1;
//
//		int selectResult = select(sockfd + 1, &readSet, nullptr, nullptr, &timeout);
//
//		if (selectResult == SOCKET_ERROR) {
//			std::cerr << "select failed with error: " << WSAGetLastError() << std::endl;
//			break;
//		}
//
//		if (selectResult > 0) {
//
//			memset(buffer, 0, sizeof(buffer));
//			int valRead = recv(sockfd, buffer, sizeof(buffer), 0);
//
//			if (strlen(buffer) == 0) {
//				std::cout << "No coordinates from the User !!" << std::endl;
//				break;
//			}
//
//			//std::cout << buffer << "\n" << std::endl;
//
//			accumulatedData += buffer;
//
//			size_t delimiterPos;
//			while ((delimiterPos = accumulatedData.find("/n")) != std::string::npos) {
//				std::string packet = accumulatedData.substr(0, delimiterPos); // Extract one complete packet
//				accumulatedData = accumulatedData.substr(delimiterPos + 2); // Remove the processed packet
//
//				//std::cout << "Received Packet: " << packet << std::endl;
//				int x, y;
//				std::istringstream iss(packet);
//				if (!(iss >> x >> y)) {
//					std::cerr << "Failed to parse integers from buffer" << std::endl;
//					return -1;
//				}
//				SetCursorPos(x, y);
//			}
//		}
//	}
//}