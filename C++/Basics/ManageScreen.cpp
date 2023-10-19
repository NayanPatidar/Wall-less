#include <iostream>
#include <X11/Xlib.h>
#include <thread>

const int MIN_X = 500;
const int MAX_X = 1000;
const int MIN_Y = 300;
const int MAX_Y = 600;
const int POLLING_INTERVAL_MS = 10;

void moveCursor(int x, int y, Display *display) {
    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, x, y);
        XFlush(display);
}

void cursorPosition(int x, int y, int loopNumX, int loopNumY){
    if (loopNumX == 1 && loopNumY == 1)
    {
    std::cout << "Cursor position - X: " << x-500 << ", Y: " << y-300 << std::endl;
    } else if (loopNumX == 2 && loopNumY == 2)
    {
    std::cout << "Cursor position - X: " << x << ", Y: " << y << std::endl;
    } else if (loopNumX == 1 && loopNumY == 2)
    {
    std::cout << "Cursor position - X: " << x-500 << ", Y: " << y << std::endl;
    } else if (loopNumX == 2 && loopNumY == 1)
    {
    std::cout << "Cursor position - X: " << x << ", Y: " << y-300 << std::endl;
    }
}

int main() {
    int loopNumX, loopNumY = 1;

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
            if(x <= MIN_X){
                x = MAX_X-1;
                loopNumX--;
                std::cout << "Loop " << loopNumX << " X " << std::endl;
            }
            if(x >= MAX_X){
                x = MIN_X+1;
                loopNumX++;
                std::cout << "Loop " << loopNumX << " X " << std::endl;
            }
            if(y <= MIN_Y){
                y = MAX_Y-1;
                loopNumY--;
                std::cout << "Loop " << loopNumY << " Y " << std::endl;
            }
            if(y >= MAX_Y){
                y = MIN_Y+1;
                loopNumY++;
                std::cout << "Loop " << loopNumY << " Y " << std::endl;
            }
                moveCursor(x,y,display);
            prevX = x;
            prevY = y;
            }
            cursorPosition(x,y, loopNumX, loopNumY);

        } 
        std::this_thread::sleep_for(std::chrono::milliseconds(POLLING_INTERVAL_MS));
    }
}
