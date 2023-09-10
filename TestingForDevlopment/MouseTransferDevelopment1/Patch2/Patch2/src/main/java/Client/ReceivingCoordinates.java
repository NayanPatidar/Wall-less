package Client;

import java.awt.*;
import java.awt.event.InputEvent;
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
			}


		}
	}
}
