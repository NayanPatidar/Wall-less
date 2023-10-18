#include <iostream>
#include <thread>
#include "Connection.cpp"

class Main{
    private:
    int sockTCP, clientSocket;
    ConnecTCP connecTCP;
    int numOfClients = 2;

    public:
        int controller(){
        
        if (connecTCP.initialize(numOfClients) < 0) {
        std::cerr << "Initialization failed." << std::endl;
        return -1;
        } 

        std::thread connectionThread([this]() { this -> buildingConnection();});
        connectionThread.join();

        return 0;
        }

        int buildingConnection(){

        int clients = numOfClients;
        while (clients > 0) {
        // accepting the connection form the client
        int clientSocket = connecTCP.acceptClient();
        if (clientSocket < 0) {
            std::cerr << "Failed to accept client." << std::endl;
            return -1;
        }

        char buffer[1024];
        int bytesRead = connecTCP.readFromTheClient(clientSocket, buffer, sizeof(buffer));
        clients--;
        }
        return 0;
        }
};