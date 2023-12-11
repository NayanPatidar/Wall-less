#ifndef KEYS_H
#define KEYS_H

#include <boost/asio.hpp>

class KeysSharing{
    public:
        void connection();

        void keyAnalyzer(boost::asio::ip::tcp::socket);
};

#endif 