#include <iostream>
#include <arpa/inet.h>
#include <cstring>
#include <unistd.h>

int main() {
    // Creating a socket 
    int TCPSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (TCPSocket == -1) {
        std::cerr << "Got Error While Creating the TCP Socket ";
        return -1;
    }

    // Using the socket functionality
    struct sockaddr_in serverAddress, clientAddress;
    memset(&serverAddress, 0, sizeof(serverAddress));
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_port = htons(8085);
    serverAddress.sin_addr.s_addr = INADDR_ANY;

    if (bind(TCPSocket, (struct sockaddr *)&serverAddress, sizeof(serverAddress)) < 0) {
        perror("Binding failed");
        return -1;
    }

    if (listen(TCPSocket, 3) < 0) {
        perror("Listen failed");
        return -1;
    }

    std::cout << "Server listening on port 8085" << std::endl;

    // Accept Incoming Connections
    int clientSocket;
    struct sockaddr_in clientAddr;
    socklen_t addrLen = sizeof(struct sockaddr_in);

    if ((clientSocket = accept(TCPSocket, (struct sockaddr *)&clientAddr, &addrLen)) < 0) {
        perror("Accept failed");
        return -1;
    }

    std::cout << "Connection accepted from " << inet_ntoa(clientAddr.sin_addr) << std::endl;

    // Reading the data from the client 
    char buffer[1024];
    int valread = read(clientSocket, buffer, sizeof(buffer));
    std::cout << "Received: " << buffer << std::endl;

    const char *response = "Hell Yeah";
    send(clientSocket, response, strlen(response), 0);
    std::cout << "Response Sent" << std::endl;

    close(clientSocket);
    close(TCPSocket);

    return 0;
}
