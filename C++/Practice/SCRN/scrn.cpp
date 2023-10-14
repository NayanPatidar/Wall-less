#include <X11/Xlib.h>
#include <X11/Xutil.h>

int main() {
    Display *display = XOpenDisplay(NULL);
    if (!display) {
        
        return 1;
    }

    int screen = XDefaultScreen(display);
    Window rootWindow = XRootWindow(display, screen);
    Window window = XCreateSimpleWindow(display, rootWindow, 0, 0, 800, 600, 0, 0, 0);

    // Allocate the grey color
    XColor greyColor;
    Colormap colormap = XDefaultColormap(display, screen);
    Status status = XAllocNamedColor(display, colormap, "grey", &greyColor, &greyColor);
    if (!status) {
        
        return 1;
    }

    // Set the background color to grey
    XSetWindowAttributes windowAttributes;
    windowAttributes.background_pixel = greyColor.pixel;

    // This will remove the window manager decorations, including the title bar
    windowAttributes.override_redirect = true;

    XChangeWindowAttributes(display, window, CWBackPixel | CWOverrideRedirect, &windowAttributes);

    // Map and manage window
    XMapWindow(display, window);
    XFlush(display);

    while (1) {
        XEvent event;
        XNextEvent(display, &event);

        // Handle events as needed
    }

    XCloseDisplay(display);
    return 0;
}
