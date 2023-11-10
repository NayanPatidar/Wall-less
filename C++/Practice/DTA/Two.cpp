#include <iostream>
#include <vector>
#include <regex>

class stringSplit{
    public:
        void split(std::vector<std::string>* entities,
                   std::map<std::string, std::vector<std::string>>* splitEntities,
                   std::vector<std::string>* nameOfEntities,
                   std::string data){

    splitEntities = new std::map<std::string, std::vector<std::string>>;
    
    // Triple space pattern
    std::regex thirdSpace("\\s{3,}");
    // Single space pattern
    std::regex singleSpace("\\s{1,}");      

    // Empty End Iterator
    std::sregex_token_iterator end;

    // First breakig the stirng by 3 space
    std::sregex_token_iterator threeSpaceIterator(data.begin(), data.end(), thirdSpace, -1);

    while (threeSpaceIterator != end) {
        // std::cout << "1" << std::endl;
        (*entities).push_back(*threeSpaceIterator++);
    }

    // Second breaking the string by Single space  
    for (std::string& entity : *entities) {
        // Use std::regex_token_iterator to split each entity by single spaces
        std::sregex_token_iterator singleSpaceIterator(entity.begin(), entity.end(), singleSpace, -1);
            int position = 0;
            std::string name;
        while (singleSpaceIterator != end) {
            if(position == 0){
            // std::cout << *singleSpaceIterator << std::endl;
            name = *singleSpaceIterator;
            }
            if (position >= 1) {
            // Print the token starting from index 2
            // std::cout << *singleSpaceIterator << std::endl;
            (*splitEntities)[name].push_back(*singleSpaceIterator);

            }
            ++singleSpaceIterator;
            ++position;
        }
    }       

    // Third extracting the names
    for (const std::string& entity : *entities) {
        std::sregex_token_iterator it(entity.begin(), entity.end(), singleSpace, -1);
        if (it != end) {
            // std::cout << *it << std::endl;
            (*nameOfEntities).push_back(*it);
        }
    } 
    // ---------------------------------------
    std::cout << "Entities Vector:" << std::endl;
    for (const std::string& entity : *entities) {
        std::cout << entity << std::endl;
    }

    std::cout << "SplitEntities Vector:" << std::endl;
    for (const auto& entity : *splitEntities) {
        // std::cout << entity.first << std::endl;
        
        const std::vector<std::string>& values = entity.second;
        std::cout << "Name : " << entity.first << ", Values : ";
        for(const std::string& value : values){
            std::cout << value << " ";
        }   
        std::cout << std::endl;
    }

    std::cout << "NameOfEntities Vector:" << std::endl;
    for (const std::string& entity : *nameOfEntities) {
        std::cout << entity << std::endl;
    }
    }
};

int main(){
        std::vector<std::string> entities;
        std::map<std::string, std::vector<std::string>> splitEntities;
        std::vector<std::string> nameOfEntities;

    std::string DataFromClient = "KING L R L   QUEEN R   Nayan L M A";
            stringSplit stringSplitup;
            stringSplitup.split(&entities, &splitEntities, &nameOfEntities, DataFromClient);
}