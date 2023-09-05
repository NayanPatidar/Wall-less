package Server;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Main {
	static JFrame jFrame = new JFrame();

	// All the sockets - UDP and TCP
	DatagramSocket datagramSocket;
	ServerSocket serverSocket;
	Socket socket;

	// Client Scree Size
	String clientScreenSize;

	// Ports and Address
	int portUDP = 12345;
	int portTCP = 12346;
	InetAddress inetAddress;

	// To verify if the connection is established or not
	boolean connectionEstablished = false;

	// Connections
	boolean TCPDone = false;
	boolean UDPDone = false;

	public Main(){

		// Creating JFrame
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		jFrame.setSize(screenWidth, screenHeight);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setOpacity(0.05f);

// Initialize sockets
		try {
			inetAddress = InetAddress.getByName("10.200.233.99");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}

		System.out.println("ENDED");
	}

	private void UDPConnection() {
		System.out.println("Here in UDPConnection");
		try {

			// General Requirements for the connection
			datagramSocket = new DatagramSocket(portUDP);
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
			serverSocket = new ServerSocket(portTCP);
			socket = serverSocket.accept();
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
