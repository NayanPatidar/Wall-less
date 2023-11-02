#include <iostream>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <X11/Xlib.h>
#include <thread>
#include <sstream>

const int MIN_X = 700;
const int MAX_X = 800;
const int MIN_Y = 350;
const int MAX_Y = 450;
const int POLLING_INTERVAL_MS = 1;
const int CLIENT_WIDTH = 1280;
const int CLIENT_HEIGHT = 720;
int loopNumX = 1;
int loopNumY = 1;
int MAX_LOOPS_X;
int MAX_LOOPS_Y;

void moveCursor(int x, int y, Display *display) {
    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, x, y);
        XFlush(display);
}

void cursorPosition(int VPofX, int VPofY, int clientSocket){
    int x = (VPofX-700)+(loopNumX-1)*100;
    int y = (VPofY-350)+(loopNumY-1)*100;
    // std::cout << x << "-" << loopNumX << "  " << y << "-" << loopNumY << std::endl;
    std::string coordiantes = std::to_string(x) + " " + std::to_string(y) + "\n";
    const char* coorToSend = coordiantes.c_str();
    int dataLength = coordiantes.length();
    send(clientSocket, coorToSend, dataLength, 0);
}


int CalculateX(int x){
    if(x < MIN_X){
        if(loopNumX == 1){
        x = MIN_X;
        } else if(loopNumX > 1){
        x = MAX_X-1;
        loopNumX--;
        }
    }
    else if (x > MAX_X){
        if(loopNumX == MAX_LOOPS_X){
        x = MAX_X;
        } else if(loopNumX < MAX_LOOPS_X){
        x = MIN_X;
        loopNumX++;
        }
    }
    else if (loopNumX == MAX_LOOPS_X){
        if(x > MIN_X + CLIENT_WIDTH%100){
        x = MIN_X + CLIENT_WIDTH%100;
        }
    }
    return x;
}

int CalculateY(int y){
    if(y < MIN_Y){
        if(loopNumY == 1){
        y = MIN_Y;
        } else if(loopNumY > 1){
        y = MAX_Y-1;
        loopNumY--;
        }    
    }
    else if (y > MAX_Y){
        if(loopNumY == MAX_LOOPS_Y){
        y = MAX_Y;
        } else if(loopNumY < MAX_LOOPS_Y){
        y = MIN_Y;
        loopNumY++;
        }    
    }
    else if (loopNumY == MAX_LOOPS_Y){
        if(y > MIN_Y + CLIENT_HEIGHT%100){
        y = MIN_Y + CLIENT_HEIGHT%100;
        }
    }
    return y;
}

int main(){
    Display* display = XOpenDisplay(NULL);
    if(display == NULL){
        std::cerr << "Error in opening the display";
    }

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

    std::cout << "Connection accepted from " << inet_ntoa(clientAddr.sin_addr) << std::endl;

    // ----------------------------------------------------------------------------------



    if(CLIENT_WIDTH%100 == 0){
        MAX_LOOPS_X = CLIENT_WIDTH/100;
    } else {
        MAX_LOOPS_X = (CLIENT_WIDTH/100) + 1;
    }

    if(CLIENT_HEIGHT%100 == 0){
        MAX_LOOPS_Y = CLIENT_HEIGHT/100;
    } else {
        MAX_LOOPS_Y = (CLIENT_HEIGHT/100) + 1;
    }
    std::cout << "MAX_LOOPS - " << MAX_LOOPS_X << std::endl;

    XEvent event;
        if (XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                      &event.xbutton.root, &event.xbutton.window,
                      &event.xbutton.x_root, &event.xbutton.y_root,
                      &event.xbutton.x, &event.xbutton.y, &event.xbutton.state)) {
                                  
            int x = event.xbutton.x;
            int y = event.xbutton.y;
            
            std::cout << x%100 << std::endl;
            loopNumX = x/100+1;         
            std::cout << x/100+1 << std::endl;   
            x = MIN_X + x%100;

            std::cout << y%100 << std::endl;
            loopNumY = y/100+1;         
            std::cout << y/100+1 << std::endl;   
            y = MIN_Y + y%100;
            moveCursor(x, y, display);
            
    }

    while (true)
    {
    XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                      &event.xbutton.root, &event.xbutton.window,
                      &event.xbutton.x_root, &event.xbutton.y_root,
                      &event.xbutton.x, &event.xbutton.y, &event.xbutton.state);

            // event.xbutton.x and event.xbutton.y contain the cursor coordinates
            int x = event.xbutton.x;
            int y = event.xbutton.y;    
            // std::cout << x << " " << y << std::endl;
            int oldX,oldY;
            // if (x != oldX || y != oldY){
            int VPofX = CalculateX(x);
            int VPofY = CalculateY(y);

            cursorPosition(VPofX, VPofY, clientSocket);

            moveCursor(VPofX, VPofY, display);
            
            oldX = x;
            oldY = y;
        //  }

        std::this_thread::sleep_for(std::chrono::milliseconds(POLLING_INTERVAL_MS));
    }

     close(clientSocket);
     close(serverSock);

    return 0;
}