#include <X11/Xlib.h>
// #include <X11/keysym.h>
#include <stdio.h>
#include <iostream>
#include <X11/XKBlib.h>
#include <X11/X.h>
#include <X11/Xatom.h>
#include <thread>
#include <cstdio> 

int main() {
    Display *display;
    Window root, window;

    // Open the display
    display = XOpenDisplay(NULL);
    if (!display) {
        fprintf(stderr, "Unable to open display.\n");
        return 1;
    }

    int screen = DefaultScreen(display);
    int screenWidth = DisplayWidth(display, screen);
    int screenHeight = DisplayHeight(display, screen);

    root = DefaultRootWindow(display);
    window = XCreateSimpleWindow(
        display,
        root,
        0, 0,
        screenWidth,
        screenHeight,
        0, 0,
        0
    );

        Atom opacityController = XInternAtom(display, "_NET_WM_WINDOW_OPACITY", False);
    unsigned long levelTransp = (unsigned long)(0xffffffff * 0);
    XChangeProperty(display, window, opacityController, XA_CARDINAL, 32, PropModeReplace
                    , (unsigned char*)&levelTransp, 1);

                        XColor dummyColor;
    Pixmap cursorPixmap = XCreateBitmapFromData(display, window, "", 1, 1);
    Cursor invisibleCursor = XCreatePixmapCursor(display, cursorPixmap, cursorPixmap, &dummyColor,
                            &dummyColor, 0, 0);

                                Atom wm_state = XInternAtom(display, "_NET_WM_STATE", False);
    Atom wm_state_above = XInternAtom(display, "_NET_WM_STATE_ABOVE", False);
    XChangeProperty(display, window, wm_state, XA_ATOM, 32, PropModeReplace, (unsigned char*)&wm_state_above, 1);

    
    // hidign the cursor
    XDefineCursor(display, window, invisibleCursor);


    XSelectInput(display, window, KeyPressMask | KeyReleaseMask);
    XMapWindow(display, window);

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

    // Close the display (this will never be reached in this example)
    XCloseDisplay(display);

    

    return 0;
}
