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
                    }
										case "1'" -> {
											robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
										}
                    case "2" -> {
	                    robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                    }
										case "2'" -> {
                        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                    }
                    case "3" -> {
	                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    }
										case "3'" -> {
											robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
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
				}
			}
		}
	}
}
