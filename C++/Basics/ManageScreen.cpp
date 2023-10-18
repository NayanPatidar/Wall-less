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
#include <chrono>
#include <thread>

void moveCursor(int x, int y, Display *display) {
    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, x, y);
        XFlush(display);
}

void cursorPosition(int x, int y){
    std::cout << "Cursor position - X: " << x-500 << ", Y: " << y-300 << std::endl;
}

int main() {


    Display* display = XOpenDisplay(NULL);
    if (display == NULL) {
        fprintf(stderr, "Unable to open the display of Ubuntu\n");
        return 1;
    }
        Window root = DefaultRootWindow(display);
        XEvent event;
        int prevX = -1, prevY = -1;

    while (true) {
        if (XQueryPointer(display, root, &event.xbutton.root, &event.xbutton.window,
                          &event.xbutton.x_root, &event.xbutton.y_root,
                          &event.xbutton.x, &event.xbutton.y,
                          &event.xbutton.state)) {
            // event.xbutton.x and event.xbutton.y contain the cursor coordinates
            int x = event.xbutton.x;
            int y = event.xbutton.y;
            if (x != prevX || y != prevY) {
            if(x <= 500)
                x = 500;
            if(x >= 1000){
                x = 1000;
            }
            if(y <= 300){
                y = 300;
            }
            if(y >= 600){
                y = 600;
            }
            moveCursor(x, y, display);
            prevX = x;
            prevY = y;
            }
                        cursorPosition(x,y);

        } 
        std::this_thread::sleep_for(std::chrono::milliseconds(10));
    }
}
