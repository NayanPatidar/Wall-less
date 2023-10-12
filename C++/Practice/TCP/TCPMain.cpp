#include <X11/Xlib.h>
#include <iostream>
#include <arpa/inet.h>
#include <cstring>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/time.h>

int set_non_blocking(int socket_fd) {
    int flags = fcntl(socket_fd, F_GETFL, 0);
    if (flags == -1) {
        perror("fcntl");
        return -1;
    }
    if (fcntl(socket_fd, F_SETFL, flags | O_NONBLOCK) == -1) {
        perror("fcntl");
        return -1;
    }
    return 0;
}

int main() {
    // Moving Cursor
    Display* display = XOpenDisplay(NULL);
    if (display == NULL) {
        fprintf(stderr, "Unable to open the display of Ubuntu\n");
        return 1;
    }

    // Creating a socket
    int TCPSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (TCPSocket == -1) {
        std::cerr << "Got Error While Creating the TCP Socket\n";
        return 1;
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

        // Set the client socket to non-blocking
    if (set_non_blocking(clientSocket) == -1) {
        std::cerr << "Failed to set non-blocking socket\n";
        close(clientSocket);
        close(TCPSocket);
        return 1;
    }

    std::cout << "Connection accepted from " << inet_ntoa(clientAddr.sin_addr) << std::endl;

    char buffer[1024];
    int oldX = 0, oldY = 0;

    while (1) {
        int valread = read(clientSocket, buffer, sizeof(buffer));
        // std::cout << "Read " << valread << " bytes. ";

        char *token = strtok(buffer, " ");

        while (1) {
        fd_set read_fds;
        FD_ZERO(&read_fds);
        FD_SET(clientSocket, &read_fds);

        struct timeval timeout;
        timeout.tv_sec = 0;
        timeout.tv_usec = 0;

        int ready = select(clientSocket + 1, &read_fds, NULL, NULL, &timeout);
        if (ready < 0) {
            perror("select");
            break;
        }

        if (FD_ISSET(clientSocket, &read_fds)) {
            int valread = read(clientSocket, buffer, sizeof(buffer));
            std::cout << buffer << std::endl;
            if (valread <= 0) {
                // Handle client disconnect or error
                std::cerr << "Client disconnected or error occurred\n";
                break;
            }

                char *token = strtok(buffer, " ");
                while (token != nullptr) {
                    int newX = std::stoi(token);    
                    token = strtok(nullptr, " ");
                    if (token == nullptr)
                    break;

                    int newY = std::stoi(token);

                    if (newX != oldX || newY != oldY) {
                    oldX = newX;
                    oldY = newY;
                    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, newX, newY);
                    XFlush(display);
                }
            }
        }

        std::memset(buffer, 0, sizeof(buffer));
    }
    

        std::memset(buffer, 0, sizeof(buffer));
    }

    XFlush(display);
    XCloseDisplay(display);

    close(clientSocket);
    close(TCPSocket);

    return 0;
}
