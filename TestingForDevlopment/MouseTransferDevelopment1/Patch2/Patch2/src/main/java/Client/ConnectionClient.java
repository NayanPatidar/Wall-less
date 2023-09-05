package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ConnectionClient {
	DatagramSocket datagramSocket;

	public ConnectionClient(){
		UDPConnection();
	}

	public void UDPConnection() {
		try {
			datagramSocket = new DatagramSocket();

			while (true) {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				datagramSocket.receive(receivePacket);
				String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
				System.out.println("Server: " + serverMessage);

				if (serverMessage.equals("StartingUDP")) {
					String acknowledgment = "Got it";
					byte[] acknowledgmentData = acknowledgment.getBytes();
					DatagramPacket acknowledgmentPacket = new DatagramPacket(acknowledgmentData, acknowledgmentData.length,
									receivePacket.getAddress(), receivePacket.getPort());
					datagramSocket.send(acknowledgmentPacket);
				}
			}
		} catch (Exception e) {
			System.out.println("Error Found: " + e.getMessage());
		}
	}
}