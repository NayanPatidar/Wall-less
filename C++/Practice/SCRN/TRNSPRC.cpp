#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xatom.h>

int main() {
  Display *display = XOpenDisplay(NULL);
  if (display == NULL) {
    return 1;
  }

  Window window = XCreateSimpleWindow(display, RootWindow(display, DefaultScreen(display)),
                                      100, 100, 200, 200, 0, 0, WhitePixel(display, DefaultScreen(display)));

  Atom opacity_atom = XInternAtom(display, "_NET_WM_WINDOW_OPACITY", False);
  unsigned long alpha = (unsigned long) (0xffffffff * 0.75);  // Example: set alpha to 75%
  XChangeProperty(display, window, opacity_atom, XA_CARDINAL, 32, PropModeReplace,
                 (unsigned char*)&alpha, 1);

  XSelectInput(display, window, ExposureMask | StructureNotifyMask);
  XMapWindow(display, window);

  while (1) {
    XEvent event;
    XNextEvent(display, &event);

    switch (event.type) {
      case Expose:
        // Handle expose event
        break;
      case ConfigureNotify:
        // Handle configure notify event
        break;
      case DestroyNotify:
        // Handle destroy notify event
        break;
      default:
        // Ignore other events
        break;
    }
  }

  XCloseDisplay(display);

  return 0;
}
