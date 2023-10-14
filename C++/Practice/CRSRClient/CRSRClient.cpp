#include <iostream>
#include <string>
#include <winsock2.h>
#include <Windows.h>

#pragma comment(lib, "Ws2_32.lib")  // Link with Ws2_32.lib

const int port = 8085;

int main() {
    WSAData wsaData;
    POINT point;

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        std::cerr << "WSAStartup failed with error: " << WSAGetLastError() << std::endl;
        return -1;
    }

    SOCKET sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (sockfd == INVALID_SOCKET) {
        std::cerr << "Socket creation failed with error: " << WSAGetLastError() << std::endl;
        WSACleanup();
        return -1;
    }

    sockaddr_in socketAddr;
    memset(&socketAddr, 0, sizeof(socketAddr));
    socketAddr.sin_family = AF_INET;
    socketAddr.sin_port = htons(port);
    socketAddr.sin_addr.s_addr = inet_addr("10.200.233.107");
    if (socketAddr.sin_addr.s_addr == INADDR_NONE) {
        std::cerr << "inet_addr failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return -1;
    }

    if (connect(sockfd, reinterpret_cast<sockaddr*>(&socketAddr), sizeof(socketAddr)) == SOCKET_ERROR) {
        std::cerr << "Connection failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return -1;
    }

    const char* checkClientPresence = "Starting:TCP";
    if (send(sockfd, checkClientPresence, strlen(checkClientPresence), 0) == SOCKET_ERROR) {
        std::cerr << "send failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return -1;
    }
    std::cout << "Data sent: " << checkClientPresence << std::endl;

    char buffer[1024];
    memset(buffer, 0, sizeof(buffer));
    int valRead = recv(sockfd, buffer, sizeof(buffer), 0);
    std::cout << "Received: " << buffer << std::endl;

    if (strcmp(buffer, "Ready") == 0) {
        std::cout << "Server is ready as hell!" << std::endl;
    }

    if (shutdown(sockfd, SD_SEND) == SOCKET_ERROR) {
        std::cerr << "Shutdown failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return -1;
    }

    closesocket(sockfd);
    WSACleanup();

    return 0;
}
