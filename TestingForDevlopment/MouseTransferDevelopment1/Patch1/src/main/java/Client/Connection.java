package Client;

import java.io.IOException;
import java.net.*;

public class Connection {
	DatagramSocket datagramSocketForReceiving;
	DatagramSocket datagramSocketForSending;
	int senderPort = 12346;
	int receivingPort = 12345;
	InetAddress inetAddress;

	public Connection() {
		establishConnection();
	}

	private void establishConnection() {
		try {
			inetAddress = InetAddress.getByName("10.200.233.99");
			DatagramSocket datagramSocket = new DatagramSocket();

			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			datagramSocket.receive(receivePacket);

			String responseMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
			System.out.println("Response from server: " + responseMessage);



		} catch (IOException e) {
			throw new RuntimeException(e);
		}
//		try {
//			datagramSocketForSending = new DatagramSocket(senderPort);
//			datagramSocketForReceiving = new DatagramSocket(receivingPort);
//
//
//		} catch (SocketException e) {
//			throw new RuntimeException(e);
//		}


	}
}
