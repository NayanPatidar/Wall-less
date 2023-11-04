#include <boost/asio.hpp>
#include <iostream>
#include <X11/Xlib.h>
#include <chrono> 
#include <thread>
#include <boost/archive/text_oarchive.hpp>
#include <boost/archive/text_iarchive.hpp>
#include <boost/serialization/string.hpp>

using namespace boost::asio;

const int WAITING_INTERVAL = 3;
const int THRESHOLD_INACTIVITY = 1000; // if the cursor is not moving for 1 sec or more ...
const int MIN_X = 700;
const int MAX_X = 800;
const int MIN_Y = 350;
const int MAX_Y = 450;
const int POLLING_INTERVAL_MS = 10;
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

void cursorPosition(int VPofX, int VPofY){
    int x = (VPofX-700)+(loopNumX-1)*100;
    int y = (VPofY-350)+(loopNumY-1)*100;
    std::cout << x << "-" << loopNumX << "  " << y << "-" << loopNumY << std::endl;
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

void initializer(){
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
}

void initialMovement(Display* display){
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
}

struct Position{
    int x;
    int y;
    template <typename Archive>
    void serialize(Archive &ar, const unsigned int version){
        ar& x;
        ar& y;
    }
};

int main(){
    // Creating the display from the X Library
    Display* display = XOpenDisplay(NULL);
    if (display == NULL){
        std::cerr << "Got error while opening the display";
    }

    // Creating the I/O operation service 
    io_service io;
    // Opening the service for clients for ipv4 and on the port 8085
    ip::tcp::acceptor clientAcceptor(io, ip::tcp::endpoint(ip::tcp::v4(), 8085));
    // Now openign the socket for the above conditions
    ip::tcp::socket socket(io);
    // Accepting the client connections
    clientAcceptor.accept(socket);
    // Creating error code given by the system
    boost::system::error_code ignored_error;

    // Creating varialbe of the struct 
    Position Pos;

    int oldX = -1;
    int oldY = -1;
    std::chrono::steady_clock::time_point lastMovementTime = std::chrono::steady_clock::now();

    // Cursor Loop Initializer
    // Also makes the initial Movement
    initializer();
    initialMovement(display);
    XEvent event;

    while(true){
        if (XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                          &event.xbutton.root, &event.xbutton.window,
                          &event.xbutton.x_root, &event.xbutton.y_root,
                          &event.xbutton.x, &event.xbutton.y, &event.xbutton.state)) {
            
                Pos.x = event.xbutton.x;
                Pos.y = event.xbutton.y;

                int VPofX = CalculateX(Pos.x);
                int VPofY = CalculateY(Pos.y);

                cursorPosition(VPofX, VPofY);
                moveCursor(VPofX, VPofY, display);

                if (std::abs(VPofX- oldX) > 0 || std::abs(VPofY - oldY) > 0){
                    lastMovementTime = std::chrono::steady_clock::now();
                }

                if (std::chrono::duration_cast<std::chrono::milliseconds>(
                    std::chrono::steady_clock::now() - lastMovementTime).count() > THRESHOLD_INACTIVITY){
                    continue;
                }

                oldX = VPofX;
                oldY = VPofY;
    }

}
