#include <iostream>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>


int main(){

    int serverSock = socket(AF_INET, SOCK_STREAM, 0);
    if(serverSock == -1){
        std::cerr << "Error: Failed to create server socket." << std::endl;
        exit(1);
    }

    struct sockaddr_in serverAddr;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(8085);

    if(bind(serverSock, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) == -1){
        std::cerr << "Error: Bind failed." << std::endl;
        exit(1);
    }

    if(listen(serverSock, 5) == -1){
        std::cerr << "Error: Listen failed." << std::endl;
        exit(1);
    }

    struct sockaddr_in clientAddr;
    socklen_t clientAddrLen = sizeof(clientAddr);
    int clientSocket = accept(serverSock, (struct sockaddr*)&clientAddr, &clientAddrLen);
    if (clientSocket == -1) {
        std::cerr << "Error: Accept failed." << std::endl;
        exit(1);
    }

    const char* message = "Hello, client!";
    send(clientSocket, message, strlen(message), 0);

     close(clientSocket);
     close(serverSock);

    return 0;
}