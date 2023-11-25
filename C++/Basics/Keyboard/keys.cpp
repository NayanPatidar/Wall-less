#include <iostream>
#include <X11/Xlib.h>
#include <X11/X.h>
#include <X11/Xatom.h>
#include <X11/Xutil.h>
#include <thread>
#include <X11/XKBlib.h>

bool allow = true;      

void blockKeys(Display* display, Window window) {
    KeyCode escapeKey = XKeysymToKeycode(display, XK_Escape);
    KeyCode altKey = XKeysymToKeycode(display, XK_Alt_L);
    KeyCode shiftKey = XKeysymToKeycode(display, XK_Shift_L);
    KeyCode ctrlKey = XKeysymToKeycode(display, XK_Control_L);
    KeyCode tabKey = XKeysymToKeycode(display, XK_Tab);

    XGrabKey(display, tabKey, Mod1Mask, window, True, GrabModeAsync, GrabModeAsync);

    XGrabKey(display, escapeKey, Mod1Mask, window, True, GrabModeAsync, GrabModeAsync);
    XGrabKey(display, altKey, Mod1Mask, window, True, GrabModeAsync, GrabModeAsync);
    XGrabKey(display, shiftKey, ShiftMask, window, True, GrabModeAsync, GrabModeAsync);
    XGrabKey(display, ctrlKey, ControlMask, window, True, GrabModeAsync, GrabModeAsync);
}

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
        500,
        500,
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
    blockKeys(display, window);
    XMapWindow(display, window);
    XGrabKeyboard(display, root, True, GrabModeAsync, GrabModeAsync, CurrentTime);


    XEvent ev;
    while (1) {
        XNextEvent(display, &ev);

        if (ev.type == KeyPress) {
        KeyCode keycode = ev.xkey.keycode;
        KeySym keysym = XkbKeycodeToKeysym(display, keycode, 0, 0);

        // Check if Alt + Tab is pressed and skip processing
        if (keysym == 'q') {
            std::cout << "GOt Q" << std::endl;
                XUngrabKeyboard(display, CurrentTime);
              // Skip processing Alt + Tab
        }

        printf("Key pressed: %c (KeySym: %lu, KeyCode: %u)\n", (char)keysym, keysym, keycode);
        }else if (ev.type == KeyRelease) {
            KeyCode keycode = ev.xkey.keycode;
            KeySym keysym = XkbKeycodeToKeysym(display, keycode, 0, 0);
            printf("Key released: %c (KeySym: %lu, KeyCode: %u)\n", (char)keysym, keysym, keycode);
        }
    }
    XUngrabKeyboard(display, CurrentTime);
    // Close the display (this will never be reached in this example)
    XCloseDisplay(display);

    

    return 0;
}
