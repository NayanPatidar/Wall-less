#include <iostream>
#include <X11/Xlib.h>
#include <X11/X.h>
#include <X11/Xatom.h>
#include <X11/Xutil.h>
#include <thread>
#include <X11/XKBlib.h>
#include <X11/extensions/XTest.h>

void suppressAltTab(Display* display) {
    // Simulate key release for Alt
    XTestFakeKeyEvent(display, XKeysymToKeycode(display, XK_Alt_L), False, CurrentTime);
    XFlush(display);

    // Simulate key release for Tab
    XTestFakeKeyEvent(display, XKeysymToKeycode(display, XK_Tab), False, CurrentTime);
    XFlush(display);
}


int main() {
    // Create an X11 connection
    Display* display = XOpenDisplay(NULL);
    if (!display) {
        fprintf(stderr, "Failed to open X11 display\n");
        exit(1);
    }

    // Grab the keyboard
    Window root = DefaultRootWindow(display);
    XGrabKeyboard(display, root, True, GrabModeAsync, GrabModeAsync, CurrentTime);

    bool altPressed = false;
    bool tabPressed = false;

    // Intercept Alt+Tab key
    XEvent event;
    while (true) {
        XNextEvent(display, &event);

        if (event.type == KeyPress) {
            KeyCode keycode = event.xkey.keycode;
            KeySym keysym = XkbKeycodeToKeysym(display, keycode, 0, 0);
            char keymap[32];
            XQueryKeymap(display, keymap);

            // Check if Alt key is pressed
            KeyCode altKeyCode = XKeysymToKeycode(display, XK_Alt_L);
            if (keymap[altKeyCode >> 3] & (1 << (altKeyCode % 8))) {
                altPressed = true;
            }

            // Check if Tab key is pressed
            KeyCode tabKeyCode = XKeysymToKeycode(display, XK_Tab);
            if (keymap[tabKeyCode >> 3] & (1 << (tabKeyCode % 8))) {
                tabPressed = true;
            }

            // Check if Alt+Tab is pressed
            if (altPressed && tabPressed) {
                printf("Alt+Tab Pressed (Suppressing)\n");
                altPressed = false;
                tabPressed = false;
            }
            printf("Key pressed: %c (KeySym: %lu, KeyCode: %u)\n", (char)keysym, keysym, keycode);
        } else if (event.type == KeyRelease) {
            KeyCode keycode = event.xkey.keycode;
            KeySym keysym = XkbKeycodeToKeysym(display, keycode, 0, 0);
            printf("Key released: %c (KeySym: %lu, KeyCode: %u)\n", (char)keysym, keysym, keycode);
        }
    }

    // Ungrab the keyboard
    XUngrabKeyboard(display, CurrentTime);

    // Close the X11 connection
    XCloseDisplay(display);

    return 0;
}
