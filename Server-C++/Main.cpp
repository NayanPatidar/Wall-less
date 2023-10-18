#include <iostream>
#include <arpa/inet.h>
#include <cstring>
#include <unistd.h>

class Main {
private:
    int sockTCP, clientSocket;
public:
        int connection(){
            // Creating the socket 
            sockTCP = socket(AF_INET, SOCK_STREAM, 0);
            if (sockTCP < 0){
                std::cerr << "Error while creating the socket!";
            }

            // reusing the port 
            int opt = 1;
            if (setsockopt(sockTCP, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)) < 0) {
                std::cerr << "Reuse the socket port";
            }

            // Binding the socket 
            struct sockaddr_in serverAddr, clientAddr;
            memset(&serverAddr, 0, sizeof(serverAddr));
            serverAddr.sin_family = AF_INET;
            serverAddr.sin_port = htons(8085);
            serverAddr.sin_addr.s_addr = INADDR_ANY;

            if(bind(sockTCP, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0){
                std::cerr << "Got error while binding the socket";
            }

            if (listen(sockTCP, 3) < 0) {
                std::cerr << "Listen failed";
                return -1;
            }

            std::cout << "Server listening on port 8085" << std::endl;

            // Accept Incoming Connection
            socklen_t addrLen = sizeof(struct sockaddr_in);

            if ((clientSocket = accept(sockTCP, (struct sockaddr *)&clientAddr, &addrLen)) < 0) {
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

    return 0;
        }
};