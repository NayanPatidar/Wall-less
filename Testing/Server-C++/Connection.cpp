#include <iostream>
#include <arpa/inet.h>
#include <cstring>
#include <unistd.h>
#include <vector>

class ConnecTCP {
private:
    int sockTCP, clientSocket;
    std::vector<int> clientSockets;
public: 

        // It basically initializes the sockets with the number of clients.
        int initialize(int numOfClients){
            // creating socket for tcp
            sockTCP = socket(AF_INET, SOCK_STREAM, 0);
            if (sockTCP < 0){
                std::cerr << "Error while creating the socket!";
            }

            // reusing the port 
            int opt = 1;
            if (setsockopt(sockTCP, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)) < 0) {
                std::cerr << "Reuse the socket port";
            }

            // binding the socket  
            struct sockaddr_in serverAddr;
            memset(&serverAddr, 0, sizeof(serverAddr));
            serverAddr.sin_family = AF_INET;
            serverAddr.sin_port = htons(8085);
            serverAddr.sin_addr.s_addr = INADDR_ANY;

            if(bind(sockTCP, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0){
                std::cerr << "Got error while binding the socket";
            }

            if (listen(sockTCP, numOfClients) < 0) {
                std::cerr << "Listen failed";
                return -1;
            }

            std::cout << "Server listening on port 8085" << std::endl;
            return 0;
        }

        int acceptClient(){
            struct sockaddr_in clientAddr;
            socklen_t addrLen = sizeof(struct sockaddr_in);

            if ((clientSocket = accept(sockTCP, (struct sockaddr *)&clientAddr, &addrLen)) < 0) {
            perror("Accept failed");
            return -1;
            }

            std::cout << "Connection accepted from " << inet_ntoa(clientAddr.sin_addr) << std::endl;
            clientSockets.push_back(clientSocket);

            return clientSocket; 
        }

        int readFromTheClient(int clientSocket, char *buffer, int bufferSize){
            int valRead = read(clientSocket, buffer, bufferSize);
            if (valRead < 0) {
            std::cerr << "Read failed";
            return -1;
            } 
        std::cout << "Received from client: " << buffer << std::endl;

        char msgToSend[] = "Hell Yeah";
        send(clientSocket, msgToSend, sizeof(msgToSend), 0); 
        std::cout << "Message to client: " << msgToSend << std::endl;

        return valRead;
        }

        int getServeSocket(){
            return sockTCP;
        }

        int getClientSocket(){
            return clientSocket;
        }

        std::vector<int>& getClientSockets(){
            return clientSockets;
        }
};