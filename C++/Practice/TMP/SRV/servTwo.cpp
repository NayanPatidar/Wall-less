#include <boost/asio.hpp>
#include <iostream>
#include <X11/Xlib.h>



using namespace boost::asio;

int main(){
    Display* display = XOpenDisplay(NULL);
    if(display == NULL){
        std::cerr << "Error in opening the display";
    }
    
    io_service io;
    
    ip::tcp::acceptor acceptor(io, ip::tcp::endpoint(ip::tcp::v4(), 8085));
    ip::tcp::socket socket(io);
    acceptor.accept(socket); 
    std::string message = "Hello, client!\n";
    
    boost::system::error_code ignored_error;

    XEvent event;
        if (XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                      &event.xbutton.root, &event.xbutton.window,
                      &event.xbutton.x_root, &event.xbutton.y_root,
                      &event.xbutton.x, &event.xbutton.y, &event.xbutton.state)) {
                                  
            int x = event.xbutton.x;
            int y = event.xbutton.y;
                                  
            int x = event.xbutton.x;
            int y = event.xbutton.y;
        write(socket, buffer(message), ignored_error);
        } 
    }
    return 0;
}