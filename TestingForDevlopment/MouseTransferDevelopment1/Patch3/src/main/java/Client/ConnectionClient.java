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
	OutputStream outputStream;
	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.31");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	int portUDP = 12345;

	public ConnectionClient(){
		UDPConnection();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		new ClientMouse(datagramSocket, inetAddress, clientSocket,portUDP, outputStream);
	}

	public void UDPConnection() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		String message = "Got it" + "  " + (int)dimension.getHeight() + " " + (int)dimension.getWidth();
		byte[] sendData = message.getBytes();

		try {
			datagramSocket = new DatagramSocket(12345);

			byte[] receiveData = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(datagramPacket);
			String receivedMsg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println(receivedMsg);

			if (receivedMsg.equals("StartingUDP:Left")){
				String msg = receivedMsg.substring(13);
				System.out.println(msg);
				System.out.println("Got the Msg From Server");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
				datagramSocket.send(sendPacket);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}