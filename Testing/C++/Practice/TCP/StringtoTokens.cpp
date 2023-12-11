#include <iostream>
#include <cstring>

int main(){
    char inputString[] = "9 12";
    char *token = strtok(inputString, " ");
    int X = std::stoi(token);
    int Y;
    while (token != nullptr)
    {
        token = strtok(nullptr, " ");
        if (token == nullptr){
            break;
        }
        Y = std::stoi(token);
    }

    std::cout << X << "-" << Y << "\n\n";
    
}