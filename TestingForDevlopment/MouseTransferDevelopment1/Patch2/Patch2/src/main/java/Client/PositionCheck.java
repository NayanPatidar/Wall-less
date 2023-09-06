package Client;

import Server.MouseClicks;

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

	public PositionCheck(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Point cursor = MouseInfo.getPointerInfo().getLocation();

		while (true) {
			if (cursor.getX() >= toolkit.getScreenSize().width - 1) {

				OutputStream outputStream = null;
				try {
					outputStream = clientSocket.getOutputStream();
				} catch (IOException e) {
					System.out.println("Got error in Output Stream: " + e.getMessage());
				}

				String message = "stop";
				byte[] messageBytes = message.getBytes();
				System.out.println("Sent the message");

				try {
					outputStream.write(messageBytes);
					outputStream.flush();

				} catch (IOException e) {
					System.out.println("Got an exception :" + e.getMessage());
				}
			}
		}
	}
}
