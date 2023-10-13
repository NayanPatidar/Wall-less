#include <iostream>
#include <winsock2.h>

#pragma comment(lib, "Ws2_32.lib")  // Link with Ws2_32.lib

const int PORT = 8080;
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
        serverAddr.sin_port = htons(PORT)   
}
