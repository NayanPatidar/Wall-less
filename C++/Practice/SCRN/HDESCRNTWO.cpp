#include <iostream>
#include <X11/Xlib.h>
#include <X11/X.h>
#include <X11/Xatom.h>
#include <thread>
#include <X11/XKBlib.h>
#include <cstdio> 

bool allow = true;

// void nextEvent(Display *dispaly){
//     while(allow){

//     }
// }

int main() {
    Display* display = XOpenDisplay(NULL);
    if(display == NULL) {
        std::cerr << "There was a error opening the display" << std::endl;
        return 1;
    }

    int screen = XDefaultScreen(display);
    Screen* screenSizeCalc = ScreenOfDisplay(display, screen);
    int screenWidth = WidthOfScreen(screenSizeCalc);
    int screenHeight = HeightOfScreen(screenSizeCalc);
    Window rootWindow = XRootWindow(display, screen);
    Window window  = XCreateSimpleWindow(
        display,
        rootWindow,
        0, 0,
        screenWidth,
        screenHeight,
        0, 0,
        0
    );

    // XSelectInput(display, window, KeyPressMask | KeyReleaseMask);

    // Setting the opacity of the screen 
    Atom opacityController = XInternAtom(display, "_NET_WM_WINDOW_OPACITY", False);
    unsigned long levelTransp = (unsigned long)(0xffffffff * 0);
    XChangeProperty(display, window, opacityController, XA_CARDINAL, 32, PropModeReplace
                    , (unsigned char*)&levelTransp, 1);

    // Setting the size of the cursor
    XColor dummyColor;
    Pixmap cursorPixmap = XCreateBitmapFromData(display, window, "", 1, 1);
    Cursor invisibleCursor = XCreatePixmapCursor(display, cursorPixmap, cursorPixmap, &dummyColor,
                            &dummyColor, 0, 0);
    
    // hidign the cursor
    XDefineCursor(display, window, invisibleCursor);

    // Staying on the top
    Atom wm_state = XInternAtom(display, "_NET_WM_STATE", False);
    Atom wm_state_above = XInternAtom(display, "_NET_WM_STATE_ABOVE", False);
    XChangeProperty(display, window, wm_state, XA_ATOM, 32, PropModeReplace, (unsigned char*)&wm_state_above, 1);

    // // Setting the windowAttributes
    // XSetWindowAttributes windowAttributes;
    // windowAttributes.override_redirect = true;
    // XChangeWindowAttributes(display, window, CWBackPixel | CWOverrideRedirect, &windowAttributes);

    XMapWindow(display, window);
    std::cout << "here" << std::endl;
    XEvent ev;
    while (1) {
        XNextEvent(display, &ev);

        if (ev.type == KeyPress) {
            KeyCode keycode = ev.xkey.keycode;
            KeySym keysym = XkbKeycodeToKeysym(display, keycode, 0, 0);
            printf("Key pressed: %c (KeySym: %lu, KeyCode: %u)\n", (char)keysym, keysym, keycode);
        } else if (ev.type == KeyRelease) {
            KeyCode keycode = ev.xkey.keycode;
            KeySym keysym = XkbKeycodeToKeysym(display, keycode, 0, 0);
            printf("Key released: %c (KeySym: %lu, KeyCode: %u)\n", (char)keysym, keysym, keycode);
        }
    }
    std::cout << "here" << std::endl;

    
    // XFlush(display);

    // std::this_thread::sleep_for(std::chrono::seconds(10));  // Sleep for 10 seconds or any desired duration
    // Your code here
    // std::this_thread::sleep_for(std::chrono::hours(1));


    XCloseDisplay(display);
    return 0;



}