#include <iostream>
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xatom.h>

int main() {
    // Create the display
    Display *display = XOpenDisplay(NULL);
    if (!display) {
        std::cerr << "Got error in creation of display !!";
        return 1;
    }

    // Create a simpleWindow 
    int screen = XDefaultScreen(display);
    int screenWidth = XDisplayWidth(display, screen);
    int screenHeight = XDisplayHeight(display, screen);
    Window rootWindow = XRootWindow(display, screen);
    Window window = XCreateSimpleWindow(
    display,
    rootWindow,
    0, 0,
    screenWidth, screenHeight,
    0, 0,
    0
    );

      Atom opacity_atom = XInternAtom(display, "_NET_WM_WINDOW_OPACITY", False);
  unsigned long alpha = (unsigned long) (0xffffffff * 0);  // Example: set alpha to 75%
  XChangeProperty(display, window, opacity_atom, XA_CARDINAL, 32, PropModeReplace,
                 (unsigned char*)&alpha, 1);

    //  Set the colour as grey
    XColor greyColor;
    Colormap colormap = XDefaultColormap(display, screen);
    Status status = XAllocNamedColor(display, colormap, "grey50", &greyColor, &greyColor);
    if (!status) {
        std::cerr << "Got error in setting the colour to grey !!";
        return 1;
    }

    XColor cursorColor;
    Pixmap cursorPixmap = XCreateBitmapFromData(display, window, "", 1, 1);
    Cursor cursor = XCreatePixmapCursor(display, cursorPixmap, cursorPixmap, &cursorColor, &cursorColor, 0, 0);

    // Set the window attributes 
    XSetWindowAttributes windowAttributes;
    windowAttributes.background_pixel = greyColor.pixel;

    windowAttributes.override_redirect = true;

    XChangeWindowAttributes(display, window, CWBackPixel | CWOverrideRedirect, &windowAttributes);

    // To make the window visible
    XDefineCursor(display, window, cursor);
    XMapWindow(display, window);
    XFlush(display);

    while (1) {
        XEvent event;
        XNextEvent(display, &event);
    }

    XCloseDisplay(display);
    return 0;
}