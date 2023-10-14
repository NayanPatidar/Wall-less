#include <X11/Xlib.h>
#include <X11/Xatom.h>
#include <iostream>

int main() {
    Display *display = XOpenDisplay(NULL);
    if (display == NULL) {
        std::cerr << "Unable to open X display." << std::endl;
        return 1;
    }

    Window window = XCreateSimpleWindow(display, RootWindow(display, 0), 100, 100, 100, 100, 0, BlackPixel(display, 0), WhitePixel(display, 0));

    XSetWindowAttributes attributes;
    attributes.override_redirect = True;
    XChangeWindowAttributes(display, window, CWOverrideRedirect, &attributes);

    XSetWindowBorder(display, window, 0);

    XMapWindow(display, window);

    Atom WM_DELETE_WINDOW = XInternAtom(display, "WM_DELETE_WINDOW", False);
    XSetWMProtocols(display, window, &WM_DELETE_WINDOW, 1);

    XEvent event;
    while (1) {
        XNextEvent(display, &event);
        if (event.type == Expose) {
            XFillRectangle(display, window, DefaultGC(display, 0), 0, 0, 100, 100);
        } else if (event.type == ClientMessage && event.xclient.message_type == WM_DELETE_WINDOW) {
            break;
        }
    }

    XCloseDisplay(display);
    return 0;
}
