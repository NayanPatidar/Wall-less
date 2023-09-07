package Client;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ReceivingCoordinates implements Runnable{
	DatagramSocket datagramSocket;
	PositionCheck positionCheck = new PositionCheck();
	InetAddress inetAddress;
	Socket clientSocket;
	Robot robot;
	static boolean allowed = true;

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

	public ReceivingCoordinates(){

	}

	@Override
	public void run() {
//		System.out.println("Listening on the port :" + 12346);
		byte[] buffer = new byte[1024];
		while (true){
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				datagramSocket.receive(packet);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			if (allowed) {
				PositionCheck.stopMessageSent = true;
				allowed =false;
			}
			String receivedMsg = new String(packet.getData(), 0, packet.getLength());
			String[] parts = receivedMsg.split(" ");
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
//			System.out.println(x);
			robot.mouseMove(x, y);
		}
	}
}
