#include <iostream>
#include <string>
#include <winsock2.h>

#pragma comment(lib, "Ws2_32.lib")  // Link with Ws2_32.lib

const int PORT = 8085;
int main() {
        WSAData wsaData;
        int iResult;

        iResult = WSAStartup(MAKEWORD(2, 2), &wsaData);
        if (iResult != 0){
            std::cerr << "Error in creating the socket library in Windows!";
            return -1;
        }

        SOCKET sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
        if (sockfd == INVALID_SOCKET){
            std::cerr << "Socket creation failed !";
            WSACleanup();
            return -1;
        }

        sockaddr_in serverAddr;
        memset(&serverAddr, 0, sizeof(serverAddr));
        serverAddr.sin_family = AF_INET;
        serverAddr.sin_port = htons(PORT);

        serverAddr.sin_addr.s_addr = inet_addr("10.200.233.107");
    if (serverAddr.sin_addr.s_addr == INADDR_NONE) {
        std::cerr << "inet_addr failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }

        iResult = connect(sockfd, (sockaddr*)&serverAddr, sizeof(serverAddr));
        if (iResult == SOCKET_ERROR) {
            std::cerr << "Connection failed: " << WSAGetLastError() << std::endl;
            closesocket(sockfd);
            WSACleanup();
            return -1;
        }

// Send data to the server
    const char* sendData = "Hello, server!";
    iResult = send(sockfd, sendData, strlen(sendData), 0);
    if (iResult == SOCKET_ERROR) {
        std::cerr << "send failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }
    std::cout << "Data sent: " << sendData << std::endl;

    char buffer[2048];
    memset(&buffer, 0, sizeof(buffer));
    int valRead = recv(sockfd, buffer, sizeof(buffer), 0);
    std::cout << "Recieved : " << buffer << std::endl;

    // Shutdown the connection for sending
    iResult = shutdown(sockfd, SD_SEND);
    if (iResult == SOCKET_ERROR) {
        std::cerr << "shutdown failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }

    // Cleanup and close the socket
    closesocket(sockfd);
    WSACleanup();

    return 0;
}