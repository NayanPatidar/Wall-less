#include <iostream>
#include <vector>
#include <regex>
#include <map>

int main(){
    std::string DataFromClient = "Nayan--R-Kinjal  Kinjal--L-Nayan R-Pahal  Pahal--L-Kinjal";

    // Double space pattern
    std::regex SecondSpace("\\s{2,}");    
    // Double space pattern
    std::regex firstSpace("\\s{1,}");  
    // Pattern --
    std::regex customSeparator("--");   
     // Empty End Iterator
    std::sregex_token_iterator end;

    std::vector<std::string> segments(
        std::sregex_token_iterator(DataFromClient.begin(), DataFromClient.end(), SecondSpace, -1),
        std::sregex_token_iterator()
    );

    // for(const auto &w : segments){
    //     std::cout << w << std::endl;
    // }

    std::cout << std::endl;

    std::map<std::string, std::vector<std::string>> resultMap;
    for (const auto &segment : segments){

        std::vector<std::string> parts(
        std::sregex_token_iterator(segment.begin(), segment.end(), customSeparator, -1),
        std::sregex_token_iterator()
    );

        std::vector<std::string> clientSides(
        std::sregex_token_iterator(parts[1].begin(), parts[1].end(), firstSpace, -1),
        std::sregex_token_iterator()
    );

        resultMap[parts[0]] = clientSides;
        std::cout << parts[0] << std::endl;
    }

    for (const auto& pair : resultMap) {
        std::cout << pair.first << ": ";
        for (const auto& value : pair.second) {
            std::cout << value << " ";
        }
        std::cout << std::endl;
    }



}