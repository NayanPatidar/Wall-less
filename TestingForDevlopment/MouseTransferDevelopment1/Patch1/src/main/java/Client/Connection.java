package Client;

import java.io.IOException;
import java.net.*;

public class Connection {
	DatagramSocket datagramSocket;
	int port = 12346;

	InetAddress inetAddress;

	public Connection() {
		establishConnection();
	}

	private void establishConnection() {
		UDPConnection();
		TCPConnection();
	}

	private void UDPConnection() {
		String message = "Got it";
		byte[] sendData = message.getBytes();

		try {
			// UDP Socket
			datagramSocket = new DatagramSocket(port);
			inetAddress = InetAddress.getByName("server Ip");

			byte[] receiveData = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(datagramPacket);
			String receivedMsg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

			if (receivedMsg.equals("Starting")){
				System.out.println("Got the Msg From Server");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
				datagramSocket.send(sendPacket);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void TCPConnection() {

	}
}
