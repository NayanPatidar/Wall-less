package Client;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class ServerMouse {
	DatagramSocket datagramSocket;
	Socket clientSocket;
	int port ;
	int portTCP ;

	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}


	public ServerMouse(Socket clientSocket, DatagramSocket datagramSocket, int portTCP, int port) {
		this.clientSocket = clientSocket;
		this.datagramSocket = datagramSocket;
		this.portTCP = portTCP;
		this.port= port;
		Operator();
	}

	private void Operator() {
		try {
			System.out.println("Listening on the port :" + port);
			byte[] buffer = new byte[1024];

			while (true) {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(packet);

				String receivedMsg = new String(packet.getData(), 0, packet.getLength());
				String[] parts = receivedMsg.split(" ");
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				movement(x, y);

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void movement(int x, int y) {
		robot.mouseMove(x, y);
	}


}
