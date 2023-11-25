#include <iostream>
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xatom.h>
#include <thread>
#include <chrono>

bool allow = true;

void nextEvent(Display* display) {
    while (allow) {
        if (XPending(display) > 0) {
            XEvent event;
            XNextEvent(display, &event);
            std::cout << "IN LOOP" << std::endl;
        }
    }
}

int main() {
    Display* display = XOpenDisplay(NULL);
    if (display == NULL) {
        std::cerr << "Display is not opening" << std::endl;
        return 1;
    }

    int screen = XDefaultScreen(display);
    int screenWidth = 500;
    int screenHeight = 500;
    Window rootWindow = XRootWindow(display, screen);
    Window window = XCreateSimpleWindow(
        display,
        rootWindow,
        0, 0,
        screenWidth,
        screenHeight,
        0, 0,
        0
    );

    // Set the opacity to transparent - currently 0.25
    Atom opacityController = XInternAtom(display, "_NET_WM_WINDOW_OPACITY", False);
    unsigned long levelTransp = (unsigned long) (0xffffffff * 0);
    XChangeProperty(display, window, opacityController, XA_CARDINAL, 32, PropModeReplace,
                    (unsigned char*)&levelTransp, 1);

    // Setting the Cursor size on the screen
    XColor dummyColor;
    Pixmap cursorPixmap = XCreateBitmapFromData(display, window, "", 1, 1);
    Cursor invisibleCursor = XCreatePixmapCursor(display, cursorPixmap, cursorPixmap, &dummyColor, &dummyColor, 0, 0);

    // Hide the cursor
    XDefineCursor(display, window, invisibleCursor);

    // Staying on the top
    Atom wm_state = XInternAtom(display, "_NET_WM_STATE", False);
    Atom wm_state_above = XInternAtom(display, "_NET_WM_STATE_ABOVE", False);
    XChangeProperty(display, window, wm_state, XA_ATOM, 32, PropModeReplace, (unsigned char*)&wm_state_above, 1);

    // Setting the windowAttributes
    XSetWindowAttributes windowAttributes;
    windowAttributes.override_redirect = true;
    XChangeWindowAttributes(display, window, CWBackPixel | CWOverrideRedirect, &windowAttributes);

    XMapWindow(display, window);
    XFlush(display);
    XEvent event;

    std::thread nextEventThread(nextEvent, display);

    std::this_thread::sleep_for(std::chrono::seconds(30));

    allow = false;  // Stop the event loop
    nextEventThread.join();  // Wait for the event loop thread to finish

    XUnmapWindow(display, window);
    std::this_thread::sleep_for(std::chrono::seconds(120));

    allow = true;  // Restart the event loop
    XMapWindow(display, window);

    nextEventThread = std::thread(nextEvent, display);  // Start a new event loop thread

    std::this_thread::sleep_for(std::chrono::seconds(30));

    allow = false;  // Stop the event loop
    nextEventThread.join();  // Wait for the event loop thread to finish

    XCloseDisplay(display);
    return 0;
}
