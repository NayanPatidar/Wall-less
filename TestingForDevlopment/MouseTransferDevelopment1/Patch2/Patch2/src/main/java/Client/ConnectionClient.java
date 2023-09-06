package Client;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class ConnectionClient {
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	boolean connectionEstablished;

	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.36");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	int portUDP = 12345;
	int portTCP = 12346;


	public ConnectionClient(){
		UDPConnection();
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		TCPConnection();
	}

	public void UDPConnection() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		String message = "Got it" + "  " + (int)dimension.getHeight() + " " + (int)dimension.getWidth();
		byte[] sendData = message.getBytes();

		try {
			// UDP Socket
			datagramSocket = new DatagramSocket(12345);

			byte[] receiveData = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(datagramPacket);
			String receivedMsg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println("Here");

			if (receivedMsg.equals("StartingUDP")){
				System.out.println("Got the Msg From Server");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
				datagramSocket.send(sendPacket);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void TCPConnection() {
		try {
			clientSocket = new Socket(inetAddress, portTCP);
			System.out.println("Connected to server: " + inetAddress);

			InputStream inputStream = clientSocket.getInputStream();
			OutputStream outputStream = clientSocket.getOutputStream();

			byte[] buffer = new byte[1024];
			int bytesRead = inputStream.read(buffer);
			String serverMessage = new String(buffer, 0, bytesRead);
			System.out.println("Received from server: " + serverMessage);


			if (serverMessage.equals("StartingTCP")){
				System.out.println("Got the message from server:" + serverMessage);
				String responseMessage = "Got it";
				byte[] responseBytes = responseMessage.getBytes();
				outputStream.write(responseBytes);
				connectionEstablished = true;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}