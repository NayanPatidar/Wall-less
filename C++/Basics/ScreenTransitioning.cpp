#include <iostream>
#include <X11/Xlib.h>
#include <thread>

const int MIN_X = 500;
const int MAX_X = 600;
const int MIN_Y = 400;
const int MAX_Y = 500;
const int POLLING_INTERVAL_MS = 10;
const int CLIENT_WIDTH = 1280;
const int CLIENT_HEIGHT = 720;
int loopNumX ;
int loopNumY ;

void moveCursor(int x, int y, Display *display) {
    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, x, y);
        XFlush(display);
}

int CalculateX(int x){
    if(x < MIN_X){
        x = MIN_X;
    }
    else if (x > MAX_X){
        x = MAX_X;
    }
    return x;
}

int CalculateY(int y){
    if(y < MIN_Y){
        y = MIN_Y;
    }
    else if (y > MAX_Y){
        y = MAX_Y;
    }
    return y;
}

int main(){
    Display* display = XOpenDisplay(NULL);
    if(display == NULL){
        std::cerr << "Error in opening the display";
    }


    while (true)
    {
    XEvent event;
    XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                      &event.xbutton.root, &event.xbutton.window,
                      &event.xbutton.x_root, &event.xbutton.y_root,
                      &event.xbutton.x, &event.xbutton.y, &event.xbutton.state);

            // event.xbutton.x and event.xbutton.y contain the cursor coordinates
            int x = event.xbutton.x;
            int y = event.xbutton.y;    
            std::cout << x << " " << y << std::endl;
            int oldX,oldY;

    
            
            int VPofX = CalculateX(x);
            int VPofY = CalculateY(y);

            moveCursor(VPofX, VPofY, display);
            
            
            oldX = x;
            oldY = y;

        std::this_thread::sleep_for(std::chrono::milliseconds(POLLING_INTERVAL_MS));
    }
}