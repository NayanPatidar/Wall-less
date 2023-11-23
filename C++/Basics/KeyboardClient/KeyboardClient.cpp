#include <iostream>
#include <boost/asio.hpp>

using namespace boost::asio;    

int main()
{
    io_service io;
    ip::tcp::socket socket(io);
    socket.connect(ip::tcp::endpoint(ip::address::from_string("10.200.233.107"), 8082));

    std::cout << "Connected To The Server" << std::endl;

}