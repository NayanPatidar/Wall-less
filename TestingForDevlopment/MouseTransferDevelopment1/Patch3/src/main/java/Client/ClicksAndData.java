package Client;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClicksAndData implements Runnable{
    Robot robot;
    DatagramSocket datagramSocket;
    InetAddress inetAddress;
    Socket clientSocket;
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();


    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
    public ClicksAndData(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                datagramSocket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String receivedMsg = new String(packet.getData(), 0, packet.getLength());

                if (receivedMsg.startsWith("B:")) {
                    System.out.println(receivedMsg);
                    String msg = receivedMsg.substring(2);
                    switch (msg) {
                        case "1":
                            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                            break;
                        case "1'":
                            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                            break;
                        case "2":
                            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                            break;
                        case "2'":
                            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                            break;
                        case "3":
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            System.out.println("Left Button Pressed");
                            break;
                        case "3'":
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                            System.out.println("Left Button Released");
                            break;
                    }
                } else if (receivedMsg.startsWith("M:")) {
                    System.out.println(receivedMsg);
                    String msg = receivedMsg.substring(2);

                    switch (msg) {
                        case "1":
                            robot.mouseWheel(1);
                            break;
                        case "-1":
                            robot.mouseWheel(-1);
                            break;
                    }
                } else if (receivedMsg.startsWith("T:'")) {
                    System.out.println(receivedMsg);
                    StringSelection stringSelection = new StringSelection(receivedMsg.substring(3));
                    clipboard.setContents(stringSelection, null);

                } else if (receivedMsg.startsWith("K:")) {
                    System.out.println(receivedMsg);
                    String msg = receivedMsg.substring(2);

                    switch (msg) {
                        case "65": // 'A' key
                            robot.keyPress(KeyEvent.VK_A);
                            System.out.println("Pressed 'A' key");
                            break;
                        case "66": // 'B' key
                            robot.keyPress(KeyEvent.VK_B);
                            System.out.println("Pressed 'B' key");
                            break;
                        case "67": // 'C' key
                            robot.keyPress(KeyEvent.VK_C);
                            System.out.println("Pressed 'C' key");
                            break;
                        case "68": // 'D' key
                            robot.keyPress(KeyEvent.VK_D);
                            System.out.println("Pressed 'D' key");
                            break;
                        case "69": // 'E' key
                            robot.keyPress(KeyEvent.VK_E);
                            System.out.println("Pressed 'E' key");
                            break;
                        case "70": // 'F' key
                            robot.keyPress(KeyEvent.VK_F);
                            System.out.println("Pressed 'F' key");
                            break;
                        case "71": // 'G' key
                            robot.keyPress(KeyEvent.VK_G);
                            System.out.println("Pressed 'G' key");
                            break;
                        case "72": // 'H' key
                            robot.keyPress(KeyEvent.VK_H);
                            System.out.println("Pressed 'H' key");
                            break;
                        case "73": // 'I' key
                            robot.keyPress(KeyEvent.VK_I);
                            System.out.println("Pressed 'I' key");
                            break;
                        case "74": // 'J' key
                            robot.keyPress(KeyEvent.VK_J);
                            System.out.println("Pressed 'J' key");
                            break;
                        case "75": // 'K' key
                            robot.keyPress(KeyEvent.VK_K);
                            System.out.println("Pressed 'K' key");
                            break;
                        case "76": // 'L' key
                            robot.keyPress(KeyEvent.VK_L);
                            System.out.println("Pressed 'L' key");
                            break;
                        case "77": // 'M' key
                            robot.keyPress(KeyEvent.VK_M);
                            System.out.println("Pressed 'M' key");
                            break;
                        case "78": // 'N' key
                            robot.keyPress(KeyEvent.VK_N);
                            System.out.println("Pressed 'N' key");
                            break;
                        case "79": // 'O' key
                            robot.keyPress(KeyEvent.VK_O);
                            System.out.println("Pressed 'O' key");
                            break;
                        case "80": // 'P' key
                            robot.keyPress(KeyEvent.VK_P);
                            System.out.println("Pressed 'P' key");
                            break;
                        case "81": // 'Q' key
                            robot.keyPress(KeyEvent.VK_Q);
                            System.out.println("Pressed 'Q' key");
                            break;
                        case "82": // 'R' key
                            robot.keyPress(KeyEvent.VK_R);
                            System.out.println("Pressed 'R' key");
                            break;
                        case "83": // 'S' key
                            robot.keyPress(KeyEvent.VK_S);
                            System.out.println("Pressed 'S' key");
                            break;
                        case "84": // 'T' key
                            robot.keyPress(KeyEvent.VK_T);
                            System.out.println("Pressed 'T' key");
                            break;
                        case "85": // 'U' key
                            robot.keyPress(KeyEvent.VK_U);
                            System.out.println("Pressed 'U' key");
                            break;
                        case "86": // 'V' key
                            robot.keyPress(KeyEvent.VK_V);
                            System.out.println("Pressed 'V' key");
                            break;
                        case "87": // 'W' key
                            robot.keyPress(KeyEvent.VK_W);
                            System.out.println("Pressed 'W' key");
                            break;
                        case "88": // 'X' key
                            robot.keyPress(KeyEvent.VK_X);
                            System.out.println("Pressed 'X' key");
                            break;
                        case "89": // 'Y' key
                            robot.keyPress(KeyEvent.VK_Y);
                            System.out.println("Pressed 'Y' key");
                            break;
                        case "90": // 'Z' key
                            robot.keyPress(KeyEvent.VK_Z);
                            System.out.println("Pressed 'Z' key");
                            break;
                        case "65'": // 'A' key
                            robot.keyRelease(KeyEvent.VK_A);
                            System.out.println("Released 'A' key");
                            break;
                        case "66'": // 'B' key
                            robot.keyRelease(KeyEvent.VK_B);
                            System.out.println("Released 'B' key");
                            break;
                        case "67'": // 'C' key
                            robot.keyRelease(KeyEvent.VK_C);
                            System.out.println("Released 'C' key");
                            break;
                        case "68'": // 'D' key
                            robot.keyRelease(KeyEvent.VK_D);
                            System.out.println("Released 'D' key");
                            break;
                        case "69'": // 'E' key
                            robot.keyRelease(KeyEvent.VK_E);
                            System.out.println("Released 'E' key");
                            break;
                        case "70'": // 'F' key
                            robot.keyRelease(KeyEvent.VK_F);
                            System.out.println("Released 'F' key");
                            break;
                        case "71'": // 'G' key
                            robot.keyRelease(KeyEvent.VK_G);
                            System.out.println("Released 'G' key");
                            break;
                        case "72'": // 'H' key
                            robot.keyRelease(KeyEvent.VK_H);
                            System.out.println("Released 'H' key");
                            break;
                        case "73'": // 'I' key
                            robot.keyRelease(KeyEvent.VK_I);
                            System.out.println("Released 'I' key");
                            break;
                        case "74'": // 'J' key
                            robot.keyRelease(KeyEvent.VK_J);
                            System.out.println("Released 'J' key");
                            break;
                        case "75'": // 'K' key
                            robot.keyRelease(KeyEvent.VK_K);
                            System.out.println("Released 'K' key");
                            break;
                        case "76'": // 'L' key
                            robot.keyRelease(KeyEvent.VK_L);
                            System.out.println("Released 'L' key");
                            break;
                        case "77'": // 'M' key
                            robot.keyRelease(KeyEvent.VK_M);
                            System.out.println("Released 'M' key");
                            break;
                        case "78'": // 'N' key
                            robot.keyRelease(KeyEvent.VK_N);
                            System.out.println("Released 'N' key");
                            break;
                        case "79'": // 'O' key
                            robot.keyRelease(KeyEvent.VK_O);
                            System.out.println("Released 'O' key");
                            break;
                        case "80'": // 'P' key
                            robot.keyRelease(KeyEvent.VK_P);
                            System.out.println("Released 'P' key");
                            break;
                        case "81'": // 'Q' key
                            robot.keyRelease(KeyEvent.VK_Q);
                            System.out.println("Released 'Q' key");
                            break;
                        case "82'": // 'R' key
                            robot.keyRelease(KeyEvent.VK_R);
                            System.out.println("Released 'R' key");
                            break;
                        case "83'": // 'S' key
                            robot.keyRelease(KeyEvent.VK_S);
                            System.out.println("Released 'S' key");
                            break;
                        case "84'": // 'T' key
                            robot.keyRelease(KeyEvent.VK_T);
                            System.out.println("Released 'T' key");
                            break;
                        case "85'": // 'U' key
                            robot.keyRelease(KeyEvent.VK_U);
                            System.out.println("Released 'U' key");
                            break;
                        case "86'": // 'V' key
                            robot.keyRelease(KeyEvent.VK_V);
                            System.out.println("Released 'V' key");
                            break;
                        case "87'": // 'W' key
                            robot.keyRelease(KeyEvent.VK_W);
                            System.out.println("Released 'W' key");
                            break;
                        case "88'": // 'X' key
                            robot.keyRelease(KeyEvent.VK_X);
                            System.out.println("Released 'X' key");
                            break;
                        case "89'": // 'Y' key
                            robot.keyRelease(KeyEvent.VK_Y);
                            System.out.println("Released 'Y' key");
                            break;
                        case "90'": // 'Z' key
                            robot.keyRelease(KeyEvent.VK_Z);
                            System.out.println("Released 'Z' key");
                            break;
                        case "16":
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            System.out.println("Pressed Shift");
                            break;
                        case "17":
                            robot.keyPress(KeyEvent.VK_CONTROL);
                            System.out.println("Pressed Control");
                            break;
                        case "18":
                            robot.keyPress(KeyEvent.VK_ALT);
                            System.out.println("Pressed Alt");
                            break;
                        case "16'":
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                            System.out.println("Released Shift");
                            break;
                        case "17'":
                            robot.keyRelease(KeyEvent.VK_CONTROL);
                            System.out.println("Released Control");
                            break;
                        case "18'":
                            robot.keyRelease(KeyEvent.VK_ALT);
                            System.out.println("Released Alt");
                            break;
                        case "48": // '0' key
                            robot.keyPress(KeyEvent.VK_0);
                            System.out.println("Pressed '0' key");
                            break;
                        case "49": // '1' key
                            robot.keyPress(KeyEvent.VK_1);
                            System.out.println("Pressed '1' key");
                            break;
                        case "50": // '2' key
                            robot.keyPress(KeyEvent.VK_2);
                            System.out.println("Pressed '2' key");
                            break;
                        case "51": // '3' key
                            robot.keyPress(KeyEvent.VK_3);
                            System.out.println("Pressed '3' key");
                            break;
                        case "52": // '4' key
                            robot.keyPress(KeyEvent.VK_4);
                            System.out.println("Pressed '4' key");
                            break;
                        case "53": // '5' key
                            robot.keyPress(KeyEvent.VK_5);
                            System.out.println("Pressed '5' key");
                            break;
                        case "54": // '6' key
                            robot.keyPress(KeyEvent.VK_6);
                            System.out.println("Pressed '6' key");
                            break;
                        case "55": // '7' key
                            robot.keyPress(KeyEvent.VK_7);
                            System.out.println("Pressed '7' key");
                            break;
                        case "56": // '8' key
                            robot.keyPress(KeyEvent.VK_8);
                            System.out.println("Pressed '8' key");
                            break;
                        case "57": // '9' key
                            robot.keyPress(KeyEvent.VK_9);
                            System.out.println("Pressed '9' key");
                            break;
                        case "48'": // '0' key
                            robot.keyRelease(KeyEvent.VK_0);
                            System.out.println("Pressed '0' key");
                            break;
                        case "49'": // '1' key
                            robot.keyRelease(KeyEvent.VK_1);
                            System.out.println("Released '1' key");
                            break;
                        case "50'": // '2' key
                            robot.keyRelease(KeyEvent.VK_2);
                            System.out.println("Released '2' key");
                            break;
                        case "51'": // '3' key
                            robot.keyRelease(KeyEvent.VK_3);
                            System.out.println("Released '3' key");
                            break;
                        case "52'": // '4' key
                            robot.keyRelease(KeyEvent.VK_4);
                            System.out.println("Released '4' key");
                            break;
                        case "53'": // '5' key
                            robot.keyRelease(KeyEvent.VK_5);
                            System.out.println("Released '5' key");
                            break;
                        case "54'": // '6' key
                            robot.keyRelease(KeyEvent.VK_6);
                            System.out.println("Released '6' key");
                            break;
                        case "55'": // '7' key
                            robot.keyRelease(KeyEvent.VK_7);
                            System.out.println("Released '7' key");
                            break;
                        case "56'": // '8' key
                            robot.keyRelease(KeyEvent.VK_8);
                            System.out.println("Released '8' key");
                            break;
                        case "57'": // '9' key
                            robot.keyRelease(KeyEvent.VK_9);
                            System.out.println("Released '9' key");
                            break;
                        case "32'": // Space key
                            robot.keyRelease(KeyEvent.VK_SPACE);
                            System.out.println("Released space key");
                            break;
                        case "10'": // Enter key
                            robot.keyRelease(KeyEvent.VK_ENTER);
                            System.out.println("Released Enter key");
                            break;
                        case "9'": // Tab key
                            robot.keyRelease(KeyEvent.VK_TAB);
                            System.out.println("Released Tab key");
                            break;
                        case "27'": // Esc key
                            robot.keyRelease(KeyEvent.VK_ESCAPE);
                            System.out.println("Released Esc key");
                            break;
                        case "8'": // Backspace key
                            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                            System.out.println("Released Backspace key");
                            break;
                        case "27": // Esc key
                            robot.keyPress(KeyEvent.VK_ESCAPE);
                            System.out.println("Pressed Esc key");
                            break;
                        case "8": // Backspace key
                            robot.keyPress(KeyEvent.VK_BACK_SPACE);
                            System.out.println("Pressed Backspace key");
                            break;
                        case "32": // Space key
                            robot.keyPress(KeyEvent.VK_SPACE);
                            System.out.println("Pressed space key");
                            break;
                        case "10": // Enter key
                            robot.keyPress(KeyEvent.VK_ENTER);
                            System.out.println("Pressed Enter key");
                            break;
                        case "9": // Tab key
                            robot.keyPress(KeyEvent.VK_TAB);
                            System.out.println("Pressed Tab key");
                            break;
                        case "127": // Delete key
                            robot.keyPress(KeyEvent.VK_DELETE);
                            System.out.println("Pressed Delete key");
                            break;
                        case "38": // Up Arrow
                            robot.keyPress(KeyEvent.VK_UP);
                            System.out.println("Up Arrow Key");
                            break;
                        case "40": // Down Arrow
                            robot.keyPress(KeyEvent.VK_DOWN);
                            System.out.println("Down Arrow Key");
                            break;
                        case "37": // Left Arrow
                            robot.keyPress(KeyEvent.VK_LEFT);
                            System.out.println("Left Arrow Key");
                            break;
                        case "39": // Right Arrow
                            robot.keyPress(KeyEvent.VK_RIGHT);
                            System.out.println("Right Arrow Key");
                            break;
                        case "44": // Comma (,)
                            System.out.println("Comma (,) Key");
                            robot.keyPress(KeyEvent.VK_COMMA);
                            break;
                        case "46": // Period (.)
                            System.out.println("Period (.) Key");
                            robot.keyPress(KeyEvent.VK_PERIOD);
                            break;
                        case "47": // Slash (/)
                            System.out.println("Slash (/) Key");
                            robot.keyPress(KeyEvent.VK_SLASH);
                            break;
                        case "92": // Backslash (\)
                            System.out.println("Backslash (\\) Key");
                            robot.keyPress(KeyEvent.VK_BACK_SLASH);
                            break;
                        case "91": // Open Bracket ([)
                            System.out.println("Open Bracket ([) Key");
                            robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
                            break;
                        case "93": // Close Bracket (])
                            System.out.println("Close Bracket (]) Key");
                            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
                            break;
                        case "59": // Semicolon (;)
                            System.out.println("Semicolon (;) Key");
                            robot.keyPress(KeyEvent.VK_SEMICOLON);
                            break;
                        case "222": // Quote (')
                            System.out.println("Quote (') Key");
                            robot.keyPress(KeyEvent.VK_QUOTE);
                            break;
                        case "61": // Equals (=) key
                            robot.keyPress(KeyEvent.VK_EQUALS);
                            System.out.println("Pressed Equals (=) key");
                            break;
                        case "45": // Minus (-) key
                            robot.keyPress(KeyEvent.VK_MINUS);
                            System.out.println("Pressed Minus (-) key");
                            break;
                        case "35": // End key
                            robot.keyPress(KeyEvent.VK_END);
                            System.out.println("Pressed End key");
                            break;
                        case "34": // Page Down key
                            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                            System.out.println("Pressed Page Down key");
                            break;
                        case "33": // Page Up key
                            robot.keyPress(KeyEvent.VK_PAGE_UP);
                            System.out.println("Pressed Page Up key");
                            break;
                        case "144": // Num Lock key
                            robot.keyPress(KeyEvent.VK_NUM_LOCK);
                            System.out.println("Pressed Num Lock key");
                            break;
                        case "36": // Home key
                            robot.keyPress(KeyEvent.VK_HOME);
                            System.out.println("Pressed Home key");
                            break;
                        case "192": // Backtick/Grave Accent key
                            System.out.println("Backtick/Grave Accent Key");
                            robot.keyPress(KeyEvent.VK_BACK_QUOTE);
                            break;
                        case "127'": // Delete key
                            robot.keyRelease(KeyEvent.VK_DELETE);
                            System.out.println("Released Delete key");
                            break;
                        case "38'": // Up Arrow
                            robot.keyRelease(KeyEvent.VK_UP);
                            System.out.println("Up Arrow Key Released");
                            break;
                        case "40'": // Down Arrow
                            robot.keyRelease(KeyEvent.VK_DOWN);
                            System.out.println("Down Arrow Key Released");
                            break;
                        case "37'": // Left Arrow
                            robot.keyRelease(KeyEvent.VK_LEFT);
                            System.out.println("Left Arrow Key Released");
                            break;
                        case "39'": // Right Arrow
                            robot.keyRelease(KeyEvent.VK_RIGHT);
                            System.out.println("Right Arrow Key Released");
                            break;
                        case "44'": // Comma (,)
                            System.out.println("Comma (,) Key Released");
                            robot.keyRelease(KeyEvent.VK_COMMA);
                            break;
                        case "46'": // Period (.)
                            System.out.println("Period (.) Key Released");
                            robot.keyRelease(KeyEvent.VK_PERIOD);
                            break;
                        case "47'": // Slash (/)
                            System.out.println("Slash (/) Key Released");
                            robot.keyRelease(KeyEvent.VK_SLASH);
                            break;
                        case "92'": // Backslash (\)
                            System.out.println("Backslash (\\) Key Released");
                            robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                            break;
                        case "91'": // Open Bracket ([)
                            System.out.println("Open Bracket ([) Key Released");
                            robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
                            break;
                        case "93'": // Close Bracket (])
                            System.out.println("Close Bracket (]) Key Released");
                            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
                            break;
                        case "59'": // Semicolon (;)
                            System.out.println("Semicolon (;) Key Released");
                            robot.keyRelease(KeyEvent.VK_SEMICOLON);
                            break;
                        case "222'": // Quote (')
                            System.out.println("Quote (') Key Released");
                            robot.keyRelease(KeyEvent.VK_QUOTE);
                            break;
                        case "61'": // Equals (=) key
                            robot.keyRelease(KeyEvent.VK_EQUALS);
                            System.out.println(" Equals (=) key Released");
                            break;
                        case "45'": // Minus (-) key
                            robot.keyRelease(KeyEvent.VK_MINUS);
                            System.out.println(" Minus (-) key Released");
                            break;
                        case "35'": // End key
                            robot.keyRelease(KeyEvent.VK_END);
                            System.out.println(" End key Released");
                            break;
                        case "34'": // Page Down key
                            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
                            System.out.println(" Page Down key Released");
                            break;
                        case "33'": // Page Up key
                            robot.keyRelease(KeyEvent.VK_PAGE_UP);
                            System.out.println("Released Page Up key");
                            break;
                        case "144'": // Num Lock key
                            robot.keyRelease(KeyEvent.VK_NUM_LOCK);
                            System.out.println("Released Num Lock key");
                            break;
                        case "36'": // Home key
                            robot.keyRelease(KeyEvent.VK_HOME);
                            System.out.println("Released Home key");
                            break;
                        case "192'": // Backtick/Grave Accent key
                            System.out.println("Backtick/Grave Accent Key Released");
                            robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
                            break;
                        case "112'": // F1 key
                            robot.keyRelease(KeyEvent.VK_F1);
                            System.out.println("Released F1 key");
                            break;
                        case "113'": // F2 key
                            robot.keyRelease(KeyEvent.VK_F2);
                            System.out.println("Released F2 key");
                            break;
                        case "114'": // F3 key
                            robot.keyRelease(KeyEvent.VK_F3);
                            System.out.println("Released F3 key");
                            break;
                        case "115'": // F4 key
                            robot.keyRelease(KeyEvent.VK_F4);
                            System.out.println("Released F4 key");
                            break;
                        case "116'": // F5 key
                            robot.keyRelease(KeyEvent.VK_F5);
                            System.out.println("Released F5 key");
                            break;
                        case "117'": // F6 key
                            robot.keyRelease(KeyEvent.VK_F6);
                            System.out.println("Released F6 key");
                            break;
                        case "118'": // F7 key
                            robot.keyRelease(KeyEvent.VK_F7);
                            System.out.println("Released F7 key");
                            break;
                        case "119'": // F8 key
                            robot.keyRelease(KeyEvent.VK_F8);
                            System.out.println("Released F8 key");
                            break;
                        case "120'": // F9 key
                            robot.keyRelease(KeyEvent.VK_F9);
                            System.out.println("Released F9 key");
                            break;
                        case "121'": // F10 key
                            robot.keyRelease(KeyEvent.VK_F10);
                            System.out.println("Released F10 key");
                            break;
                        case "122'": // F11 key
                            robot.keyRelease(KeyEvent.VK_F11);
                            System.out.println("Released F11 key");
                            break;
                        case "123'": // F12 key
                            robot.keyRelease(KeyEvent.VK_F12);
                            System.out.println("Released F12 key");
                            break;
                        case "112": // F1 key
                            robot.keyPress(KeyEvent.VK_F1);
                            System.out.println("Pressed F1 key");
                            break;
                        case "113": // F2 key
                            robot.keyPress(KeyEvent.VK_F2);
                            System.out.println("Pressed F2 key");
                            break;
                        case "114": // F3 key
                            robot.keyPress(KeyEvent.VK_F3);
                            System.out.println("Pressed F3 key");
                            break;
                        case "115": // F4 key
                            robot.keyPress(KeyEvent.VK_F4);
                            System.out.println("Pressed F4 key");
                            break;
                        case "116": // F5 key
                            robot.keyPress(KeyEvent.VK_F5);
                            System.out.println("Pressed F5 key");
                            break;
                        case "117": // F6 key
                            robot.keyPress(KeyEvent.VK_F6);
                            System.out.println("Pressed F6 key");
                            break;
                        case "118": // F7 key
                            robot.keyPress(KeyEvent.VK_F7);
                            System.out.println("Pressed F7 key");
                            break;
                        case "119": // F8 key
                            robot.keyPress(KeyEvent.VK_F8);
                            System.out.println("Pressed F8 key");
                            break;
                        case "120": // F9 key
                            robot.keyPress(KeyEvent.VK_F9);
                            System.out.println("Pressed F9 key");
                            break;
                        case "121": // F10 key
                            robot.keyPress(KeyEvent.VK_F10);
                            System.out.println("Pressed F10 key");
                            break;
                        case "122": // F11 key
                            robot.keyPress(KeyEvent.VK_F11);
                            System.out.println("Pressed F11 key");
                            break;
                        case "123": // F12 key
                            robot.keyPress(KeyEvent.VK_F12);
                            System.out.println("Pressed F12 key");
                            break;
                    }
                }
        }
    }
}
