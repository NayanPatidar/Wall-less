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
				case "162":
					robot.keyPress(KeyEvent.VK_CONTROL);
					System.out.println("Pressed Control");
					break;
				case "164":
					robot.keyPress(KeyEvent.VK_ALT);
					System.out.println("Pressed Alt");
					break;
				case "160'":
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
			}
		}
	}
}
