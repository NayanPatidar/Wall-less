#include <iostream>
#include <X11/Xlib.h>
#include <thread>

const int MIN_X = 500;
const int MAX_X = 1000;
const int MIN_Y = 300;
const int MAX_Y = 600;
const int POLLING_INTERVAL_MS = 10;
const int WIDTH = 1280;
const int HEIGHT = 720;
const int MAX_X_LOOPS = (WIDTH/MIN_X) + 1;
const int MAX_Y_LOOPS = (HEIGHT/MIN_Y) + 1;
const int MAX_LOOP_WIDTH = MIN_X + WIDTH - (WIDTH/MIN_X)*MIN_X;
const int MAX_LOOP_HEIGHT = MIN_Y + HEIGHT - (HEIGHT/MIN_Y)*MIN_Y;
bool initialXPosition = true;
bool initialYPosition = true;


void moveCursor(int x, int y, Display *display) {
    XWarpPointer(display, None, XRootWindow(display, XDefaultScreen(display)), 0, 0, 0, 0, x, y);
        XFlush(display);
}

void cursorPosition(int x, int y, int loopNumX, int loopNumY){
    std::cout << "Cursor position - X: " << x-500+((loopNumX-1)*500)<< ", Y: " << y-300+((loopNumY-1)*300) << std::endl;
}

int calculateXLoopNumber(int x) {
    int loop = x/(MAX_X-MIN_X);
    // std::cout << x << " " << MAX_X-MIN_X << " " << x/(MAX_X-MIN_X) << loop << std::endl;
    return loop+1;
}

int calculateYLoopNumber(int y) {
    int loop = y/(MAX_Y-MIN_Y);
    // std::cout << x << " " << MAX_Y-MIN_Y << " " << x/(MAX_Y-MIN_Y) << loop << std::endl;
    return loop+1;
}

int main() {
    int loopNumX = 1;
    int loopNumY = 1;

    Display* display = XOpenDisplay(NULL);
    if (display == NULL) {
        fprintf(stderr, "Unable to open the display of Ubuntu\n");
        return 1;
    }
        Window root = DefaultRootWindow(display);
        XEvent event;
        int prevX = -1, prevY = -1;

{
    XEvent event;
    XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                      &event.xbutton.root, &event.xbutton.window,
                      &event.xbutton.x_root, &event.xbutton.y_root,
                      &event.xbutton.x, &event.xbutton.y, &event.xbutton.state);

        if (event.xbutton.x > WIDTH){
                moveCursor(WIDTH, event.xbutton.y, display);
        } 
        if (event.xbutton.y > HEIGHT){
                moveCursor(event.xbutton.y, HEIGHT, display);
        }
        
        std::cout << "Position - X: " << event.xbutton.x << ", Y: " << event.xbutton.y << std::endl;
        std::cout << calculateXLoopNumber(event.xbutton.x) << std::endl;
        loopNumX = calculateXLoopNumber(event.xbutton.x);

        std::cout << calculateYLoopNumber(event.xbutton.y) << std::endl;
        loopNumY = calculateYLoopNumber(event.xbutton.y);
}


    while (true) {
        if (XQueryPointer(display, root, &event.xbutton.root, &event.xbutton.window,
                          &event.xbutton.x_root, &event.xbutton.y_root,
                          &event.xbutton.x, &event.xbutton.y,
                          &event.xbutton.state)) {
            // event.xbutton.x and event.xbutton.y contain the cursor coordinates
            int x = event.xbutton.x;
            int y = event.xbutton.y;
            
            if (x != prevX || y != prevY) {
            
            // std::cout << loopNumX << " " << MAX_X_LOOPS << " " << MAX_LOOP_WIDTH << std::endl;
            if(loopNumX == MAX_X_LOOPS && x > MAX_LOOP_WIDTH ){
                if (!initialXPosition){
                    x = MAX_LOOP_WIDTH;
                } else {
                    // std::cout << loopNumX << " Testing " << std::endl;
                    x = MIN_X + (x - (loopNumX-1)*(MAX_X-MIN_X));
                    // std::cout << x << " Testing " << std::endl;
                    initialXPosition = false;
                }
            }

            else if(x <= MIN_X ){
                if (loopNumX > 1)
                {
                x = MAX_X-1;
                loopNumX--;
                std::cout << "Loop " << loopNumX << " X " << std::endl;
                } else if (loopNumX == 1)
                {
                    if (initialXPosition){
                        x = MIN_X + x;

                    } else {
                         x = MIN_X ;
                    }
                }
            } else if(x >= MAX_X ){
                if ( loopNumX < MAX_X_LOOPS)
                {
                x = MIN_X+1;
                loopNumX++;
                std::cout << "Loop " << loopNumX << " X " << std::endl;
                } else if (loopNumX == MAX_X_LOOPS)
                {
                   x = MAX_X;
                }
            }

            if(loopNumY == MAX_Y_LOOPS && y > MAX_LOOP_HEIGHT){
                if (!initialYPosition){
                    y = MAX_LOOP_HEIGHT;
                } else {
                    // std::cout << loopNumX << " Testing " << std::endl;
                    y = MIN_Y + (y - (loopNumY-1)*(MAX_Y-MIN_Y));
                    // std::cout << x << " Testing " << std::endl;
                    initialYPosition = false;
                }            }
             else if(y <= MIN_Y){

                if (loopNumY > 1){
                y = MAX_Y-1;
                loopNumY--;
                std::cout << "Loop " << loopNumY << " Y " << std::endl;
                }
                    if (initialYPosition){
                        y = MIN_Y + y;
                    } else {
                         y = MIN_Y ;
                    }
            }
             else if(y >= MAX_Y){
                if (loopNumY < MAX_Y_LOOPS)
                {
                y = MIN_Y+1;
                loopNumY++;
                std::cout << "Loop " << loopNumY << " Y " << std::endl;
                } else if (loopNumY == MAX_Y_LOOPS)
                {
                    y = MAX_Y;
                }
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
