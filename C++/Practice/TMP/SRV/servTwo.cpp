#include <boost/asio.hpp>
#include <iostream>
#include <X11/Xlib.h>
#include <chrono> 
#include <thread>
#include <boost/archive/text_oarchive.hpp>
#include <boost/archive/text_iarchive.hpp>
#include <boost/serialization/string.hpp>

using namespace boost::asio;

const int POLLING_INTERVAL_MS =3 ;
const int INACTIVITY_THRESHOLD_MS = 1000; // This is for 3 seconds (3000 milisec)

struct Position{
    int x;
    int y;
    template <typename Archive>
    void serialize(Archive &ar, const unsigned int version) {
        ar & x;
        ar & y;
    }
};


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

    Position cursorPos; 
    int oldX = -1, oldY = -1;
    std::chrono::steady_clock::time_point lastActivityTime = std::chrono::steady_clock::now();

    while (true) {
        XEvent event;
        if (XQueryPointer(display, XRootWindow(display, DefaultScreen(display)),
                          &event.xbutton.root, &event.xbutton.window,
                          &event.xbutton.x_root, &event.xbutton.y_root,
                          &event.xbutton.x, &event.xbutton.y, &event.xbutton.state)) {

            cursorPos.x = event.xbutton.x;
            cursorPos.y = event.xbutton.y;
            
            if(std::abs(cursorPos.x - oldX) > 0 || std::abs(cursorPos.y - oldY) > 0 ){
                lastActivityTime = std::chrono::steady_clock::now();
            }

            if (std::chrono::duration_cast<std::chrono::milliseconds>(
                    std::chrono::steady_clock::now() - lastActivityTime).count() >= INACTIVITY_THRESHOLD_MS) {
                continue;
            }

            // if (cursorPos.x != oldX || cursorPos.y != oldY){
            std::stringstream ss;
            boost::archive::text_oarchive oa(ss);
            oa << cursorPos;

            std::string serializedData = ss.str();
            serializedData += '\n';
            std::cout << serializedData << std::endl;
            write(socket, buffer(serializedData), ignored_error);
            std::this_thread::sleep_for(std::chrono::milliseconds(POLLING_INTERVAL_MS));

            oldX = cursorPos.x;
            oldY = cursorPos.y;
            // }
        }
    }
    return 0;
}