package Client;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class PositionCheck implements Runnable{
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	OutputStream outputStream;

	public PositionCheck(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket, OutputStream outputStream) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.clientSocket = clientSocket;
		this.outputStream = outputStream;
	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		while (true) {
			Point cursor = MouseInfo.getPointerInfo().getLocation();
			if (cursor.getX() >= toolkit.getScreenSize().width - 2) {


				String message = "stop";
				byte[] messageBytes = message.getBytes();
				try {
					outputStream.write(messageBytes);
					outputStream.flush();

				} catch (IOException e) {
					System.out.println("Got an exception :" + e.getMessage());
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
