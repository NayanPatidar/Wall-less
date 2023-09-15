package Client;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ReceivingCoordinates implements Runnable{
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}


	public ReceivingCoordinates(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize().getSize();

		byte[] buffer = new byte[1024];
		while (true){
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				datagramSocket.receive(packet);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			String receivedMsg = new String(packet.getData(), 0, packet.getLength());
			if (receivedMsg.startsWith("C:")) {
				String msg = receivedMsg.substring(2);
				String[] parts = msg.split(" ");
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				robot.mouseMove(x, y);

				if (x >= dimension.width-2){
					System.out.println("Left Screen");
					robot.mouseMove(750,350);
				}
			} else if (receivedMsg.startsWith("B:")) {
				String msg = receivedMsg.substring(2);
                switch (msg) {
                    case "1" -> {
                        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
	                    break;
                    }
										case "1'" -> {
											robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
											break;
										}
                    case "2" -> {
	                    robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
	                    break;
                    }
										case "2'" -> {
											robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
											break;
										}
                    case "3" -> {
	                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
											break;
                    }
										case "3'" -> {
											robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
											break;
										}
                }
			} else if (receivedMsg.startsWith("K:")){
				String msg = receivedMsg.substring(2);
				int code = Integer.parseInt(msg);
				switch (code){
					case 32: // Space key
						robot.keyPress(KeyEvent.VK_SPACE);
						robot.keyRelease(KeyEvent.VK_SPACE);
						System.out.println("Pressed space key");
						break;
					case 10: // Enter key
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						System.out.println("Pressed Enter key");
						break;
					case 9: // Tab key
						robot.keyPress(KeyEvent.VK_TAB);
						robot.keyRelease(KeyEvent.VK_TAB);
						System.out.println("Pressed Tab key");
						break;
					case 16: // Shift key
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						System.out.println("Pressed Shift key");
						break;
					case 17: // Ctrl key
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						System.out.println("Pressed Ctrl key");
						break;
					case 18: // Alt key
						robot.keyPress(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_ALT);
						System.out.println("Pressed Alt key");
						break;
					case 27: // Esc key
						robot.keyPress(KeyEvent.VK_ESCAPE);
						robot.keyRelease(KeyEvent.VK_ESCAPE);
						System.out.println("Pressed Esc key");
						break;
					case 8: // Backspace key
						robot.keyPress(KeyEvent.VK_BACK_SPACE);
						robot.keyRelease(KeyEvent.VK_BACK_SPACE);
						System.out.println("Pressed Backspace key");
						break;
					case 20: // Caps Lock key
						toolkit.setLockingKeyState(KeyEvent.VK_CAPS_LOCK, !toolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
						System.out.println("Toggled Caps Lock");
						break;
					case 112: // F1 key
						robot.keyPress(KeyEvent.VK_F1);
						robot.keyRelease(KeyEvent.VK_F1);
						System.out.println("Pressed F1 key");
						break;
					case 113: // F2 key
						robot.keyPress(KeyEvent.VK_F2);
						robot.keyRelease(KeyEvent.VK_F2);
						System.out.println("Pressed F2 key");
						break;
					case 114: // F3 key
						robot.keyPress(KeyEvent.VK_F3);
						robot.keyRelease(KeyEvent.VK_F3);
						System.out.println("Pressed F3 key");
						break;
					case 115: // F4 key
						robot.keyPress(KeyEvent.VK_F4);
						robot.keyRelease(KeyEvent.VK_F4);
						System.out.println("Pressed F4 key");
						break;
					case 116: // F5 key
						robot.keyPress(KeyEvent.VK_F5);
						robot.keyRelease(KeyEvent.VK_F5);
						System.out.println("Pressed F5 key");
						break;
					case 117: // F6 key
						robot.keyPress(KeyEvent.VK_F6);
						robot.keyRelease(KeyEvent.VK_F6);
						System.out.println("Pressed F6 key");
						break;
					case 118: // F7 key
						robot.keyPress(KeyEvent.VK_F7);
						robot.keyRelease(KeyEvent.VK_F7);
						System.out.println("Pressed F7 key");
						break;
					case 119: // F8 key
						robot.keyPress(KeyEvent.VK_F8);
						robot.keyRelease(KeyEvent.VK_F8);
						System.out.println("Pressed F8 key");
						break;
					case 120: // F9 key
						robot.keyPress(KeyEvent.VK_F9);
						robot.keyRelease(KeyEvent.VK_F9);
						System.out.println("Pressed F9 key");
						break;
					case 121: // F10 key
						robot.keyPress(KeyEvent.VK_F10);
						robot.keyRelease(KeyEvent.VK_F10);
						System.out.println("Pressed F10 key");
						break;
					case 122: // F11 key
						robot.keyPress(KeyEvent.VK_F11);
						robot.keyRelease(KeyEvent.VK_F11);
						System.out.println("Pressed F11 key");
						break;
					case 123: // F12 key
						robot.keyPress(KeyEvent.VK_F12);
						robot.keyRelease(KeyEvent.VK_F12);
						System.out.println("Pressed F12 key");
						break;
					case 48: // '0' key
						robot.keyPress(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_0);
						System.out.println("Pressed '0' key");
						break;
					case 49: // '1' key
						robot.keyPress(KeyEvent.VK_1);
						robot.keyRelease(KeyEvent.VK_1);
						System.out.println("Pressed '1' key");
						break;
					case 50: // '2' key
						robot.keyPress(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_2);
						System.out.println("Pressed '2' key");
						break;
					case 51: // '3' key
						robot.keyPress(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_3);
						System.out.println("Pressed '3' key");
						break;
					case 52: // '4' key
						robot.keyPress(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_4);
						System.out.println("Pressed '4' key");
						break;
					case 53: // '5' key
						robot.keyPress(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_5);
						System.out.println("Pressed '5' key");
						break;
					case 54: // '6' key
						robot.keyPress(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_6);
						System.out.println("Pressed '6' key");
						break;
					case 55: // '7' key
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);
						System.out.println("Pressed '7' key");
						break;
					case 56: // '8' key
						robot.keyPress(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_8);
						System.out.println("Pressed '8' key");
						break;
					case 57: // '9' key
						robot.keyPress(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_9);
						System.out.println("Pressed '9' key");
						break;
					case 65: // 'A' key
						robot.keyPress(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_A);
						System.out.println("Pressed 'A' key");
						break;
					case 66: // 'B' key
						robot.keyPress(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_B);
						System.out.println("Pressed 'B' key");
						break;
					case 67: // 'C' key
						robot.keyPress(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_C);
						System.out.println("Pressed 'C' key");
						break;
					case 68: // 'D' key
						robot.keyPress(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_D);
						System.out.println("Pressed 'D' key");
						break;
					case 69: // 'E' key
						robot.keyPress(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_E);
						System.out.println("Pressed 'E' key");
						break;
					case 70: // 'F' key
						robot.keyPress(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_F);
						System.out.println("Pressed 'F' key");
						break;
					case 71: // 'G' key
						robot.keyPress(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_G);
						System.out.println("Pressed 'G' key");
						break;
					case 72: // 'H' key
						robot.keyPress(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_H);
						System.out.println("Pressed 'H' key");
						break;
					case 73: // 'I' key
						robot.keyPress(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_I);
						System.out.println("Pressed 'I' key");
						break;
					case 74: // 'J' key
						robot.keyPress(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_J);
						System.out.println("Pressed 'J' key");
						break;
					case 75: // 'K' key
						robot.keyPress(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_K);
						System.out.println("Pressed 'K' key");
						break;
					case 76: // 'L' key
						robot.keyPress(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_L);
						System.out.println("Pressed 'L' key");
						break;
					case 77: // 'M' key
						robot.keyPress(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_M);
						System.out.println("Pressed 'M' key");
						break;
					case 78: // 'N' key
						robot.keyPress(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_N);
						System.out.println("Pressed 'N' key");
						break;
					case 79: // 'O' key
						robot.keyPress(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_O);
						System.out.println("Pressed 'O' key");
						break;
					case 80: // 'P' key
						robot.keyPress(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_P);
						System.out.println("Pressed 'P' key");
						break;
					case 81: // 'Q' key
						robot.keyPress(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_Q);
						System.out.println("Pressed 'Q' key");
						break;
					case 82: // 'R' key
						robot.keyPress(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_R);
						System.out.println("Pressed 'R' key");
						break;
					case 83: // 'S' key
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_S);
						System.out.println("Pressed 'S' key");
						break;
					case 84: // 'T' key
						robot.keyPress(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_T);
						System.out.println("Pressed 'T' key");
						break;
					case 85: // 'U' key
						robot.keyPress(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_U);
						System.out.println("Pressed 'U' key");
						break;
					case 86: // 'V' key
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						System.out.println("Pressed 'V' key");
						break;
					case 87: // 'W' key
						robot.keyPress(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_W);
						System.out.println("Pressed 'W' key");
						break;
					case 88: // 'X' key
						robot.keyPress(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_X);
						System.out.println("Pressed 'X' key");
						break;
					case 89: // 'Y' key
						robot.keyPress(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_Y);
						System.out.println("Pressed 'Y' key");
						break;
					case 90: // 'Z' key
						robot.keyPress(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_Z);
						System.out.println("Pressed 'Z' key");
						break;
					case 46: // Delete key
						robot.keyPress(KeyEvent.VK_DELETE);
						robot.keyRelease(KeyEvent.VK_DELETE);
						System.out.println("Pressed Delete key");
						break;
				}
			}
		}
	}
}