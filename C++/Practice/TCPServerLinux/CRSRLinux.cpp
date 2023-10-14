#include <iostream>
#include <arpa/inet.h>
#include <cstring>
#include <unistd.h>
#include <sstream>
#include <X11/Xlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/time.h>

// void moveCursor(int x, int y) {
//     std::cout << "\033[" << y << ";" << x << "H";  // ESC[<y>;<x>H
//     // std::cout << "x: " << x << ", y: " << y << std::endl;   
// }

void moveCursor(int x, int y, Display *display) {
    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, x, y);
        XFlush(display);
}

int main(){

    Display* display = XOpenDisplay(NULL);
    if (display == NULL) {
        fprintf(stderr, "Unable to open the display of Ubuntu\n");
        return 1;
    }

    int TCPSocket = socket(AF_INET, SOCK_STREAM,IPPROTO_TCP);
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

    // Listening to the client 
    if(listen(TCPSocket,1) == -1 ){
            std::cerr << "Error while listening to client. ";
            close(TCPSocket);
            return 1;
    }

      std::cout << "Server listening on port 8085" << std::endl;

    int clientSocket;
    socklen_t addrLen = sizeof(struct sockaddr_in);

// Accepting the client 
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

    fd_set read_fds;
    int max_fd = std::max(clientSocket, TCPSocket) + 1;

// Taking the coordinates from the client
    while(1){
        FD_ZERO(&read_fds);
        FD_SET(clientSocket, &read_fds);

        struct timeval timeout;
        timeout.tv_sec = 0;
        timeout.tv_usec = 50;

        int ready = select(max_fd, &read_fds, NULL, NULL, &timeout);

        if (FD_ISSET(clientSocket, &read_fds)) {
        memset(buffer, 0, sizeof(buffer));
        int valRead = read(clientSocket, buffer, sizeof(buffer));

        if (strlen(buffer) == 0) {
        std::cout << "No coordinates from the User !!" << std::endl;
        break;
        }

        int x, y;
        std::istringstream iss(buffer);

        if (!(iss >> x >> y)) {
        std::cerr << "Failed to parse integers from buffer" << std::endl;
        return -1;
        }
        moveCursor(x, y, display);
        // std::cout << x  << " " << y << std::endl;
        }            
    }      
        close(clientSocket);
        close(TCPSocket);

    return 0;
}
