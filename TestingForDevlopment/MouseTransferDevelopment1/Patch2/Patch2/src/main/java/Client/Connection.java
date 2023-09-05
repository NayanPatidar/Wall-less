package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Connection {
	public boolean UDPConnection = false;
	public boolean TCPConnection = false;

	public Connection(){
		UDPClientReceiver();
		TCPClientReceiver();
	}

	public void UDPClientReceiver(){
		try (DatagramSocket clientSocket = new DatagramSocket()) {
			// Prepare a DatagramPacket for receiving data from the server
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			// Receive data from the server
			clientSocket.receive(receivePacket);

			// Extract the received message and print it
			String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

			String sendMsg = "Got it";
			byte[] response = sendMsg.getBytes();

			if (receivedMessage.equals("StartingUDP")){
				DatagramPacket datagramPacket = new DatagramPacket(response, response.length);
				clientSocket.send(datagramPacket);
				UDPConnection = true;
			}

		} catch (IOException e) {
			System.err.println("Client exception: " + e.getMessage());
		}
	}

	public void TCPClientReceiver(){
		String serverHost = "ServerIP"; // Server's hostname or IP address
		final int serverPort = 12346; // Server's TCP port
		String serverMsg = "Got it";
		byte[] buffer = serverMsg.getBytes();

		try (Socket clientSocket = new Socket(serverHost, serverPort);
		     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		     OutputStream outputStream = clientSocket.getOutputStream()) {

			System.out.println("Connected to server: " + clientSocket.getInetAddress());

			// Receive a response from the server
			String serverResponse;
			while ((serverResponse = in.readLine()) != null) {
				System.out.println("Server response: " + serverResponse);
				if (serverResponse.equals("StartingTCP")){
					outputStream.write(buffer);
					TCPConnection = true;
					break;
				}
			}
		} catch (IOException e) {
			System.err.println("Client exception: " + e.getMessage());
		}
	}
}
