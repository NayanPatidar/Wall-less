#include <iostream>
#include <map>
#include <string>
#include <vector>

int main(){
    std::map<std::string, std::vector<std::string>>* nameDirect;
    nameDirect = new std::map<std::string, std::vector<std::string>>;


    (*nameDirect)["Nayan"].push_back("L");
    (*nameDirect)["Nayan"].push_back("T");

    std::cout << "Name : Nayan , Values : ";
    for (const std::string& value : (*nameDirect)["Nayan"]){
        std::cout << value << " ";
    }
    return 0;
}
