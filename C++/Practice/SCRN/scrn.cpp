#include <iostream>
#include <X11/Xlib.h>
#include <X11/Xutil.h>

int main() {
    // Create the display
    Display *display = XOpenDisplay(NULL);
    if (!display) {
        std::cerr << "Got error in creation of display !!";
        return 1;
    }

    // Create a simpleWindow 
    int screen = XDefaultScreen(display);
    Window rootWindow = XRootWindow(display, screen);
    Window window = XCreateSimpleWindow(
    display,
    rootWindow,
    0, 0,
    800, 600,
    0, 0,
    0
    );

    //  Set the colour as grey
    XColor greyColor;
    Colormap colormap = XDefaultColormap(display, screen);
    Status status = XAllocNamedColor(display, colormap, "grey50", &greyColor, &greyColor);
    if (!status) {
        std::cerr << "Got error in setting the colour to grey !!";
        return 1;
    }

    

    // Set the window attributes 
    XSetWindowAttributes windowAttributes;
    windowAttributes.background_pixel = greyColor.pixel;

    windowAttributes.override_redirect = true;

    XChangeWindowAttributes(display, window, CWBackPixel | CWOverrideRedirect, &windowAttributes);

    // To make the window visible
    XMapWindow(display, window);
    XFlush(display);

    while (1) {
        XEvent event;
        XNextEvent(display, &event);
    }

    XCloseDisplay(display);
    return 0;
}