package Client;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class PositionCheck implements Runnable{
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	OutputStream outputStream;
	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}
	boolean stop = false;

	public PositionCheck(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket, OutputStream outputStream) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.clientSocket = clientSocket;
		this.outputStream = outputStream;
	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		while (!stop) {
			Point cursor = MouseInfo.getPointerInfo().getLocation();
			if (cursor.getX() >= toolkit.getScreenSize().width - 2) {
				String message = "stop";
				byte[] messageBytes = message.getBytes();
				try {
					outputStream.write(messageBytes);
					outputStream.flush();
					robot.mouseMove(350,350);
					stop = true;
				} catch (IOException e) {
					System.out.println("Got an exception :" + e.getMessage());
				}
			}
		}
	}
}
