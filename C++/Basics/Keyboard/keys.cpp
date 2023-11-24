// Might be using just X11 on the Linux side
#include <iostream>
#include <keys.h>
#include <boost/asio.hpp>

using namespace boost::asio;

void keysSharing::connection(){

}

void keysSharing::keyAnalyzer(ip::tcp::socket){

}

int main(){
    io_service io;
    
    ip::tcp::acceptor acceptor(io, ip::tcp::endpoint(ip::tcp::v4(), 12345));

    ip::tcp::socket socket(io);

    acceptor.accept(socket);

    std::cout << "Connection established." << std::endl;


}