package AutoHotKeysTab;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {


	public static void main(String[] args) throws IOException {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}

		DatagramSocket socket = new DatagramSocket(12345);
		while (true) {
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);

			String message = new String(packet.getData(), 0, packet.getLength());
			System.out.println("Received message:" + message);
			switch (message) {
				case "160":
					robot.keyPress(KeyEvent.VK_SHIFT);
					System.out.println("Pressed Shift");
					break;
				case "161":
					robot.keyPress(KeyEvent.VK_SHIFT);
					System.out.println("Pressed Shift");
					break;
					case "162":
					robot.keyPress(KeyEvent.VK_CONTROL);
					System.out.println("Pressed Control");
					break;
				case "164":
					robot.keyPress(KeyEvent.VK_ALT);
					System.out.println("Pressed Alt");
					break;
				case "160'" :
					robot.keyRelease(KeyEvent.VK_SHIFT);
					System.out.println("Released Shift");
					break;
				case "161'" :
					robot.keyRelease(KeyEvent.VK_SHIFT);
					System.out.println("Released Shift");
					break;
				case "162'":
					robot.keyRelease(KeyEvent.VK_CONTROL);
					System.out.println("Released Control");
					break;
				case "164'":
					robot.keyRelease(KeyEvent.VK_ALT);
					System.out.println("Released Alt");
					break;
				case "9": // Tab key
					robot.keyPress(KeyEvent.VK_TAB);
					System.out.println("Pressed Tab key");
					break;
				case "9'": // Tab key
					robot.keyRelease(KeyEvent.VK_TAB);
					System.out.println("Released Tab key");
					break;
				case "48":
					robot.keyPress(KeyEvent.VK_0);
					robot.keyRelease(KeyEvent.VK_0);
					break;
				case "49":
					robot.keyPress(KeyEvent.VK_1);
					robot.keyRelease(KeyEvent.VK_1);
					break;
				case "50":
					robot.keyPress(KeyEvent.VK_2);
					robot.keyRelease(KeyEvent.VK_2);
					break;
				case "51":
					robot.keyPress(KeyEvent.VK_3);
					robot.keyRelease(KeyEvent.VK_3);
					break;
				case "52":
					robot.keyPress(KeyEvent.VK_4);
					robot.keyRelease(KeyEvent.VK_4);
					break;
				case "53":
					robot.keyPress(KeyEvent.VK_5);
					robot.keyRelease(KeyEvent.VK_5);
					break;
				case "54":
					robot.keyPress(KeyEvent.VK_6);
					robot.keyRelease(KeyEvent.VK_6);
					break;
				case "55":
					robot.keyPress(KeyEvent.VK_7);
					robot.keyRelease(KeyEvent.VK_7);
					break;
				case "56":
					robot.keyPress(KeyEvent.VK_8);
					robot.keyRelease(KeyEvent.VK_8);
					break;
				case "57":
					robot.keyPress(KeyEvent.VK_9);
					robot.keyRelease(KeyEvent.VK_9);
					break;
				case "65":
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_A);
					break;
				case "66":
					robot.keyPress(KeyEvent.VK_B);
					robot.keyRelease(KeyEvent.VK_B);
					break;

				case "67":
					robot.keyPress(KeyEvent.VK_C);
					robot.keyRelease(KeyEvent.VK_C);
					break;
				case "68":
					robot.keyPress(KeyEvent.VK_D);
					robot.keyRelease(KeyEvent.VK_D);
					break;
				case "69":
					robot.keyPress(KeyEvent.VK_E);
					robot.keyRelease(KeyEvent.VK_E);
					break;
				case "70":
					robot.keyPress(KeyEvent.VK_F);
					robot.keyRelease(KeyEvent.VK_F);
					break;
				case "71":
					robot.keyPress(KeyEvent.VK_G);
					robot.keyRelease(KeyEvent.VK_G);
					break;
				case "72":
					robot.keyPress(KeyEvent.VK_H);
					robot.keyRelease(KeyEvent.VK_H);
					break;
				case "73":
					robot.keyPress(KeyEvent.VK_I);
					robot.keyRelease(KeyEvent.VK_I);
					break;
				case "74":
					robot.keyPress(KeyEvent.VK_J);
					robot.keyRelease(KeyEvent.VK_J);
					break;
				case "75":
					robot.keyPress(KeyEvent.VK_K);
					robot.keyRelease(KeyEvent.VK_K);
					break;
				case "76":
					robot.keyPress(KeyEvent.VK_L);
					robot.keyRelease(KeyEvent.VK_L);
					break;
				case "77":
					robot.keyPress(KeyEvent.VK_M);
					robot.keyRelease(KeyEvent.VK_M);
					break;
				case "78":
					robot.keyPress(KeyEvent.VK_N);
					robot.keyRelease(KeyEvent.VK_N);
					break;
				case "79":
					robot.keyPress(KeyEvent.VK_O);
					robot.keyRelease(KeyEvent.VK_O);
					break;
				case "80":
					robot.keyPress(KeyEvent.VK_P);
					robot.keyRelease(KeyEvent.VK_P);
					break;
				case "81":
					robot.keyPress(KeyEvent.VK_Q);
					robot.keyRelease(KeyEvent.VK_Q);
					break;
				case "82":
					robot.keyPress(KeyEvent.VK_R);
					robot.keyRelease(KeyEvent.VK_R);
					break;
				case "83":
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_S);
					break;
				case "84":
					robot.keyPress(KeyEvent.VK_T);
					robot.keyRelease(KeyEvent.VK_T);
					break;
				case "85":
					robot.keyPress(KeyEvent.VK_U);
					robot.keyRelease(KeyEvent.VK_U);
					break;
				case "86":
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					break;
				case "87":
					robot.keyPress(KeyEvent.VK_W);
					robot.keyRelease(KeyEvent.VK_W);
					break;
				case "88":
					robot.keyPress(KeyEvent.VK_X);
					robot.keyRelease(KeyEvent.VK_X);
					break;
				case "89":
					robot.keyPress(KeyEvent.VK_Y);
					robot.keyRelease(KeyEvent.VK_Y);
					break;
				case "90":
					robot.keyPress(KeyEvent.VK_Z);
					robot.keyRelease(KeyEvent.VK_Z);
					break;
				case "219": // [
					robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
					robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
					break;
				case "221": // ]
					robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
					robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
					break;
				case "186": // ;
					robot.keyPress(KeyEvent.VK_SEMICOLON);
					robot.keyRelease(KeyEvent.VK_SEMICOLON);
					break;
				case "222": // '
					robot.keyPress(KeyEvent.VK_QUOTE);
					robot.keyRelease(KeyEvent.VK_QUOTE);
					break;
				case "190": // .
					robot.keyPress(KeyEvent.VK_PERIOD);
					robot.keyRelease(KeyEvent.VK_PERIOD);
					break;
				case "191": // /
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					break;
				case "220": // \
					robot.keyPress(KeyEvent.VK_BACK_SLASH);
					robot.keyRelease(KeyEvent.VK_BACK_SLASH);
					break;
				case "189": // -
					robot.keyPress(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_MINUS);
					break;
				case "187": // =
					robot.keyPress(KeyEvent.VK_EQUALS);
					robot.keyRelease(KeyEvent.VK_EQUALS);
					break;
				case "188": // ,
					robot.keyPress(KeyEvent.VK_COMMA);
					robot.keyRelease(KeyEvent.VK_COMMA);
					break;
				case "37": // Left arrow
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyRelease(KeyEvent.VK_LEFT);
					break;
				case "38": // Up arrow
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyRelease(KeyEvent.VK_UP);
					break;
				case "39": // Right arrow
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyRelease(KeyEvent.VK_RIGHT);
					break;
				case "40": // Down arrow
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					break;
				case "27": // ESC
					robot.keyPress(KeyEvent.VK_ESCAPE);
					robot.keyRelease(KeyEvent.VK_ESCAPE);
					break;
				case "8": // Backspace
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
					break;
				case "33": // Page Up
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyRelease(KeyEvent.VK_PAGE_UP);
					break;
				case "34": // Page Down
					robot.keyPress(KeyEvent.VK_PAGE_DOWN);
					robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
					break;
				case "36": // Home
					robot.keyPress(KeyEvent.VK_HOME);
					robot.keyRelease(KeyEvent.VK_HOME);
					break;
				case "35": // End
					robot.keyPress(KeyEvent.VK_END);
					robot.keyRelease(KeyEvent.VK_END);
					break;
				case "32": // Space
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_SPACE);
					break;
				case "10": // Return (or Enter)
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					break;
				case "20": // CapsLock
					robot.keyPress(KeyEvent.VK_CAPS_LOCK);
					break;
				case "20'": // CapsLock
					robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
					break;

			}
		}
	}
}
//