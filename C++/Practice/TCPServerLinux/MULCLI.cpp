#include <boost/asio.hpp>
#include <iostream>

using boost::asio::ip::tcp;

void handleClient(tcp::socket clientSocket, tcp::acceptor& acceptor) {
    try {
        // Handle client communication here
        boost::asio::streambuf buffer;
        boost::system::error_code error;
        while (true) {
            size_t len = boost::asio::read_until(clientSocket, buffer, '\n', error);

            if (error) {
                std::cerr << "Got Error: " << error.message() << std::endl;
                break;
            }

            std::istream is(&buffer);
            std::string message;
            std::getline(is, message);

            // Process and respond to the client's message
            std::cout << "Received: " << message << std::endl;

            // You can send a response back to the client here
        }

        // After handling the client, wait for the next client connection
        acceptConnection(acceptor);
    } catch (std::exception& e) {
        std::cerr << "Exception in handleClient: " << e.what() << std::endl;
    }
}

void acceptConnection(tcp::acceptor& acceptor) {
    tcp::socket clientSocket(acceptor.get_executor());
    acceptor.async_accept(clientSocket, [&](boost::system::error_code ec) {
        if (!ec) {
            handleClient(std::move(clientSocket), acceptor);
        } else {
            std::cerr << "Got error" << std::endl;
        }
    });
}

int main() {
    boost::asio::io_service io_service;
    tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), 8082)); // Change the port as needed

    acceptConnection(acceptor); // Start accepting connections

    io_service.run(); // Start the event loop

    return 0;
}
