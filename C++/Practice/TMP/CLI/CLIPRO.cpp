#include <iostream>
#include <boost/asio.hpp>
#include <boost/array.hpp>
#include <boost/archive/text_iarchive.hpp>

using namespace boost::asio;

struct Position
{
    int x;
    int y;
    template <typename Archive>
    void serialize(Archive& ar, const unsigned int version) {
        ar& x;
        ar& y;
    }
};

Position deserialization(const std::string& data) {
    std::istringstream ss(data);
    boost::archive::text_iarchive ar(ss);
    Position cursorPosition;
    ar >> cursorPosition;
    return cursorPosition;
}

int main()
{
    POINT cursor;
    io_service io;
    ip::tcp::socket socket(io);
    ip::tcp::resolver resolver(io);

    ip::tcp::resolver::query query("10.200.233.107", "8085");
    ip::tcp::resolver::iterator endpoint_iterator = resolver.resolve(query);

    connect(socket, endpoint_iterator);

    std::string message;
    boost::system::error_code error;
    
    while (true) {
        char buf[128];
        size_t len = read(socket, buffer(buf), error);

        if(error) {
            std::cerr << "Error: " << error.message() << std::endl;
            break;
        }
        message.append(buf, len);

        while (true) {

            size_t newlinePos = message.find('\n');
            //std::cout << newlinePos << std::endl;

            if (newlinePos == std::string::npos) {
                break;  
            }
            std::string completeMessage = message.substr(0, newlinePos);
            Position cursorPosition = deserialization(completeMessage);
            std::cout << "Received Position: (" << cursorPosition.x << ", " << cursorPosition.y << ")" << std::endl;
            SetCursorPos(cursorPosition.x, cursorPosition.y);
            message = message.substr(newlinePos + 1);  // Remove the processed message
        }
    }
    return 0;
}

