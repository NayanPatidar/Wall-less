#include <iostream>
#include <WS2tcpip.h>
#include <string>
#include <thread>

#define _WINSOCK_DEPRECATED_NO_WARNINGS

#pragma comment (lib, "ws2_32.lib")
using namespace std;

SOCKET sendSock;
sockaddr_in clientAddr;

bool shiftPress = false;
bool altPress = false;
bool ctrlPress = false;
bool TabPress = false;




LRESULT CALLBACK LowLevelKeyboardProc(int nCode, WPARAM wParam, LPARAM lParam) {

    // Check if Caps Lock is ON
    short capsLockState = GetKeyState(VK_CAPITAL);
    bool capsLockOn = (capsLockState & 1) != 0;

    if (nCode == HC_ACTION) {
        KBDLLHOOKSTRUCT* pKeyboard = reinterpret_cast<KBDLLHOOKSTRUCT*>(lParam);
        DWORD key = pKeyboard->vkCode;

        if ((pKeyboard->vkCode >= 'A' && pKeyboard->vkCode <= 'Z') ||
            (pKeyboard->vkCode >= 'a' && pKeyboard->vkCode <= 'z')) {
            string key_string = to_string(key);
            const char* message = key_string.c_str();
            // Handle character key press
            if (wParam == WM_KEYDOWN || wParam == WM_SYSKEYDOWN) {
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                std::cout << "Character key pressed: " << char(pKeyboard->vkCode) << std::endl;
                return 1;
            }
        }

        if ((pKeyboard->vkCode >= '0' && pKeyboard->vkCode <= '9') ||
            (pKeyboard->vkCode >= VK_NUMPAD0 && pKeyboard->vkCode <= VK_NUMPAD9)) {
            string key_string = to_string(key);
            const char* message = key_string.c_str();
            // Handle number key press
            if (wParam == WM_KEYDOWN || wParam == WM_SYSKEYDOWN) {
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                std::cout << "Number key pressed: " << char(pKeyboard->vkCode) << std::endl;
            }
        }

        if (wParam == WM_KEYDOWN || wParam == WM_SYSKEYDOWN) {

            string key_string = to_string(key);
            const char* message = key_string.c_str();

            switch (key) {
            case VK_LSHIFT: {
                if (!shiftPress) {
                    std::cout << "Shift Pressed" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    shiftPress = true;
                    return 1;

                }
                break;
            }
            case VK_RSHIFT: {
                if (!shiftPress) {
                    std::cout << "Shift Pressed" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    shiftPress = true;
                    return 1;
                }
                break;
            }
            case VK_LMENU: {
                if (!altPress) {
                    std::cout << "Alt Pressed" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    altPress = true;
                    return 1;

                }
                break;
            }
            case VK_RMENU: {
                if (!altPress) {
                    std::cout << "Alt Pressed" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    altPress = true;
                    return 1;

                }
                break;
            }
            case VK_LCONTROL: {
                if (!ctrlPress) {
                    std::cout << "Control Pressed" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    ctrlPress = true;
                    return 1;

                }
                break;
            }
            case VK_TAB: {
                if ((GetAsyncKeyState(VK_MENU) & 0x8000) != 0) {
                    std::cout << "Tab + Alt Pressed (Suppressing)" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    return 1;  // Suppress the Tab + Alt key combination
                }
                else if (ctrlPress) {
                    std::cout << "Ctrl + Tab Pressed (Suppressing)" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    return 1;  // Suppress the Ctrl + Tab key combination
                }
                else {
                    std::cout << "Tab Pressed" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                    return 1;
                }
                break;
            }
            case VK_ESCAPE: {
                std::cout << "Escape Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;
                break;
            }
            case VK_OEM_COMMA: {
                std::cout << "Comma (,) Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_PERIOD: {
                std::cout << "Period (.) Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_1: {
                std::cout << "Semicolon (;) Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_UP: {
                std::cout << "Up Arrow Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_DOWN: {
                std::cout << "Down Arrow Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_LEFT: {
                std::cout << "Left Arrow Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_RIGHT: {
                std::cout << "Right Arrow Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_4: {
                std::cout << "Left Square Bracket ([) Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_6: {
                std::cout << "Right Square Bracket (]) Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_2: {
                // ... Handle forward slash (/) key
                std::cout << "forward slash (/) ke" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_5: {
                // ... Handle backslash (\) key
                std::cout << "backslash slash (/) key" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_MINUS: {
                // ... Handle minus (-) key
                std::cout << "- slash (-) key" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_PLUS: {
                // ... Handle equals (=) key
                std::cout << "equal slash (= key" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_HOME: {
                std::cout << "Home Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_PRIOR: {
                std::cout << "Page Up Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_NEXT: {
                std::cout << "Page Down Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_END: {
                std::cout << "End Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_3: {
                std::cout << "Backtick (`) Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_OEM_7: {
                std::cout << "Single Quote (') Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_BACK: {
                std::cout << "Backspace Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_SPACE: {
                std::cout << "Space Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_RETURN: {
                std::cout << "Enter Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_DELETE: {
                std::cout << "Delete Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;

                break;
            }
            case VK_CAPITAL: {
                string key_string_caps = to_string(key) + "'";
                const char* capsmsg = key_string_caps.c_str();

                if (capsLockOn) {
                    std::cout << "Caps Lock is OFF" << std::endl;
                    int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                }
                else {
                    std::cout << "Caps Lock is ON" << std::endl;
                    int sendResult = sendto(sendSock, capsmsg, strlen(capsmsg), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                }
                break;
            }
            case VK_LWIN: {
                std::cout << "Left Windows Key Pressed" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                return 1;
                break;
            }
            }

        }
        else if (wParam == WM_KEYUP || wParam == WM_SYSKEYUP) {
            std::cout << "Key released: " << key << std::endl;

            string key_string = to_string(key) + "'";
            const char* message = key_string.c_str();

            switch (key) {
            case VK_LSHIFT: {
                std::cout << "Shift Released" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                shiftPress = false;
                break;
            }
            case VK_RSHIFT: {
                std::cout << "Shift Released" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                shiftPress = false;
                break;
            }
            case VK_LMENU: {
                std::cout << "Alt Released" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                altPress = false;
                break;
            }
            case VK_LCONTROL: {
                std::cout << "Control Released" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                ctrlPress = false;
                break;
            }
            case VK_TAB: {
                std::cout << "Tab Released" << std::endl;
                int sendResult = sendto(sendSock, message, strlen(message), 0, (sockaddr*)&clientAddr, sizeof(clientAddr));
                TabPress = false;
                break;
            }

            }
        }
    }

    return CallNextHookEx(NULL, nCode, wParam, lParam);
}

int main()
{

    WSADATA wsaData;
    int wsaStartupResult = WSAStartup(MAKEWORD(2, 2), &wsaData);

    // Checking Winsock with error handling 
    if (wsaStartupResult != 0) {
        cerr << "WSAStartup failed with error: " << wsaStartupResult << endl;
        return 1;
    }

    // Creating socket for receiving
    SOCKET sockfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);

    if (sockfd == INVALID_SOCKET) {
        cerr << "Socket creation failed with error: " << WSAGetLastError() << std::endl;
        WSACleanup();
        return 1;
    }

    // Socket binding 
    sockaddr_in serverAddr;
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY; // Bind to any available network interface
    serverAddr.sin_port = htons(12345);

    if (bind(sockfd, (sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        std::cerr << "Socket bind failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }

    std::cout << "Server listening on port 12345...\n";

    // Create a socket for sending
    sendSock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
    if (sendSock == INVALID_SOCKET) {
        cerr << "Socket creation for sending failed with error: " << WSAGetLastError() << std::endl;
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }

    // Client address to send to
    memset(&clientAddr, 0, sizeof(clientAddr));
    clientAddr.sin_family = AF_INET;
    inet_pton(AF_INET, "10.200.233.107", &clientAddr.sin_addr);  // Change to the appropriate client IP address
    clientAddr.sin_port = htons(12345);  // Change to the appropriate client port



    HHOOK hook = SetWindowsHookExA(WH_KEYBOARD_LL, LowLevelKeyboardProc, GetModuleHandle(NULL), 0);

    if (hook == NULL) {
        std::cerr << "Failed to set up hook." << std::endl;
        return 1;
    }

    // Message loop to keep the application running
    MSG msg;
    while (GetMessage(&msg, NULL, 0, 0)) {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    // Unhook and exit
    UnhookWindowsHookEx(hook);


    return 0;
}