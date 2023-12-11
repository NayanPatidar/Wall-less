#include <iostream>
#include <vector>
#include <regex>

int main() {
    std::string input = "KING L R L   QUEEN R   Nayan L M A";
    std::vector<std::string> entities;

    // Triple Space expression Pattern
    std::regex pattern("\\s{3,}");
    std::regex singleSpace("\\s{1,}");

    // Use std::regex_token_iterator to split the input string
    std::sregex_token_iterator it(input.begin(), input.end(), pattern, -1);
    std::sregex_token_iterator end;

    while (it != end) {
        entities.push_back(*it++);
    }

    // Create a new vector to store the further split parts
    std::vector<std::string> splitEntities;

    for (const std::string& entity : entities) {
        // Use std::regex_token_iterator to split each entity by single spaces
                std::cout << entity << std::endl;
        std::sregex_token_iterator two(entity.begin(), entity.end(), singleSpace, -1);
        while (two != end) {
            splitEntities.push_back(*two++);
        }
    }

    std::cout << "----------------\n" << std::endl;

    for (const std::string& entity : splitEntities) {
        std::cout << entity << std::endl;
    }

    std::cout << "----------------\n" << std::endl;
    std::vector<std::string>* nameOfEntities;

    for (const std::string& entity : entities) {
        // Print the first string obtained after splitting by three spaces
        std::sregex_token_iterator it(entity.begin(), entity.end(), singleSpace, -1);
        if (it != end) {
            std::cout << *it << std::endl;
            (*nameOfEntities).push_back(*it);
        }
    }

    for (const std::string& entity : *nameOfEntities) {
        std::cout << entity << std::endl;
    } 
    return 0;
}