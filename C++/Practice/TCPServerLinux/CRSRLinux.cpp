#include <iostream>
#include <arpa/inet.h>
#include <cstring>
#include <unistd.h>


int main(){
    int TCPSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (TCPSocket == -1) {
        std::cerr << "Got Error While Creating the TCP Socket ";
        return -1;
    }

    int opt = 1;
if (setsockopt(TCPSocket, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)) < 0) {
    perror("setsockopt");
    return -1;
}


    // Binding the socket with the sockaddr_in 
    struct sockaddr_in serverAddr, clientAddr;
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(8085);
    serverAddr.sin_addr.s_addr = INADDR_ANY;

    // Binding the socket
    if(bind(TCPSocket, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0){
        std::cerr << "Got Error while Binding. ";
        return -1;
    }

    if(listen(TCPSocket,1) == -1 ){
            std::cerr << "Error while listening to client. ";
            close(TCPSocket);
            return 1;
    }

      std::cout << "Server listening on port 8085" << std::endl;

    int clientSocket;
    socklen_t addrLen = sizeof(struct sockaddr_in);

    if ((clientSocket = accept(TCPSocket, (sockaddr*)&clientAddr, &addrLen)) < 0) {
        perror("Accept failed");
        return -1;
    }

    std::cout << "Connection accepted from " << inet_ntoa(clientAddr.sin_addr) << std::endl;

    char buffer[1024];
    memset(buffer, 0, sizeof(buffer));
    int valRead = read(clientSocket, buffer, sizeof(buffer));
    std::cout << "Received: " << buffer << std::endl;

    const char *msgForClient = "Ready";
    send(clientSocket, msgForClient, strlen(msgForClient), 0);
    std::cout << "Response Sent" << std::endl;

    close(clientSocket);
    close(TCPSocket);

    return 0;
}