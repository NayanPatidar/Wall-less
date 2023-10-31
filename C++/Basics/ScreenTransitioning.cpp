#include <iostream>
#include <X11/Xlib.h>
#include <thread>

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
        }    }
    return y;
}

int main(){
    Display* display = XOpenDisplay(NULL);
    if(display == NULL){
        std::cerr << "Error in opening the display";
    }

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
            x = MIN_X + x%100;
            std::cout << x/100+1 << std::endl;  
            loopNumX = x/100+1;          

            
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
            
            int VPofX = CalculateX(x);
            int VPofY = CalculateY(y);

            cursorPosition(VPofX, VPofY);

            moveCursor(VPofX, VPofY, display);
            
            oldX = x;
            oldY = y;

        std::this_thread::sleep_for(std::chrono::milliseconds(POLLING_INTERVAL_MS));
    }
}