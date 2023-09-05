package Server;

import java.io.*;
import java.net.*;

public class Connection {

	// All the sockets - UDP and TCP
	DatagramSocket datagramSocket;
	ServerSocket serverSocket;
	Socket socket;

	// Ports and Address
	int portUDP;
	int portTCP;
	InetAddress inetAddress;

	// ScreenSize
	String clientScreenSize;

	// Connections
	boolean TCPDone = false;
	boolean UDPDone = false;

	public Connection(DatagramSocket datagramSocket, ServerSocket serverSocket, Socket socket, InetAddress inetAddress, int portUDP, int portTCP) {
		this.datagramSocket = datagramSocket;
		this.serverSocket = serverSocket;
		this.socket = socket;
		this.inetAddress = inetAddress;
		this.portTCP = portTCP;
		this.portUDP = portUDP;
	}

	private void UDPConnection() {
		try {

			// General Requirements for the connection
			byte[] receiveData = new byte[1024];
			String MessageToSend = "StartingUDP";
			byte[] sendData = MessageToSend.getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			System.out.println("UDP Connection starting on port: 12345");

			while (true) {
				//Sending the message to the client to verify connection
				datagramSocket.send(datagramPacket);

				//Receiving the response from the client of the verification
				datagramSocket.receive(receivePacket);

				String receivedMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
				String[] clientMsg = receivedMsg.split("  ");
				System.out.println(clientMsg[0]);
				clientScreenSize = clientMsg[1];
				if (clientMsg[0].equals("Got it")) {
					System.out.println("Got the Message from Client: " + receivedMsg);
					UDPDone = true;
					break;
				}
			}
		} catch (UnknownHostException | SocketException e) {
			System.out.println("Error sending message: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Got IO Exception.");
		}
	}

	private void TCPConnection() {
		try {

			// General Requirements for the connection
			String sendingUDPMsg = "StartingTCP";
			OutputStream outputStream = socket.getOutputStream();
			byte[] sendingMsg = sendingUDPMsg.getBytes();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			byte[] buffer = new byte[1024];

			while (true) {
				outputStream.write(sendingMsg);

				String clientMessage;
				while ((clientMessage = in.readLine()) != null) {
					System.out.println("Received from client: " + clientMessage);
					if (clientMessage.equals("Got it")){
						TCPDone = true;
							break;
					}
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
