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
	Robot robot;
	static public boolean stopMessageSent = false;

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

	public PositionCheck(){

	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		String message = "stop";
		ReceivingCoordinates receivingCoordinates = new ReceivingCoordinates();

		while (true) {
				Point cursor = MouseInfo.getPointerInfo().getLocation();
			if (cursor.getX() >= toolkit.getScreenSize().width - 1 && stopMessageSent) {
				byte[] messageBytes = message.getBytes();
				try {
					outputStream.write(messageBytes);
					outputStream.flush();
					System.out.println("here");
					robot.mouseMove((int) (cursor.getX()-3),cursor.y);
					stopMessageSent  = false;
					Thread.sleep(10);
					ReceivingCoordinates.allowed = true;


				} catch (IOException e) {
					System.out.println("Got an exception: " + e.getMessage());
				} catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
		}
	}
}
