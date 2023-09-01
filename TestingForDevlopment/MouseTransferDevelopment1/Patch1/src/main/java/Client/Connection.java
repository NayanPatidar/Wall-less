package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Connection {
	DatagramSocket datagramSocket;
	int port = 12345;
	int portTCP = 12346;
	boolean connectionEstablished;

	InetAddress inetAddress;

	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.131");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection() {
		establishConnection();
	}

	private void establishConnection() {
		UDPConnection();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		TCPConnection();
	}

	private void UDPConnection() {
		String message = "Got it";
		byte[] sendData = message.getBytes();

		try {
			// UDP Socket
			datagramSocket = new DatagramSocket(port);

			byte[] receiveData = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(datagramPacket);
			String receivedMsg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println("Here");

			if (receivedMsg.equals("StartingUDP")){
				System.out.println("Got the Msg From Server");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
				datagramSocket.send(sendPacket);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void TCPConnection() {

		try (Socket clientSocket = new Socket(inetAddress, portTCP)){

			System.out.println("Connected to server: " + inetAddress);

			InputStream inputStream = clientSocket.getInputStream();
			byte[] buffer = new byte[1024];
			int bytesRead = inputStream.read(buffer);
			String serverMessage = new String(buffer, 0, bytesRead);
			System.out.println("Received from server: " + serverMessage);


			if (serverMessage.equals("StartingTCP")){
				System.out.println("Got the message from server:" + serverMessage);
				connectionEstablished = true;
			}



		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
