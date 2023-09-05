package Client;

import java.awt.*;
import java.io.IOException;
import java.net.*;

public class ConnectionClient {
	DatagramSocket datagramSocket;
	InetAddress inetAddress;

	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.131");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	int portUDP = 12345;

	public ConnectionClient(){
		UDPConnection();
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
}