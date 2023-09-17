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
                    case "1"  -> robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
					case "1'" -> robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                    case "2"  -> robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
					case "2'" -> robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                    case "3"  -> robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					case "3'" -> robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
			} else if (receivedMsg.startsWith("K:")){
				String msg = receivedMsg.substring(2);

				switch (msg){
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
				}

			}
		}
	}
}