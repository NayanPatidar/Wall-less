#include <iostream>

int main(){
    int width = 1280;
    int virtualScreenWidth = 500;
    int loops = width/virtualScreenWidth;
    std::cout << loops << " " << std::endl;
    std::cout << width-(loops*virtualScreenWidth) << " " << std::endl;
}