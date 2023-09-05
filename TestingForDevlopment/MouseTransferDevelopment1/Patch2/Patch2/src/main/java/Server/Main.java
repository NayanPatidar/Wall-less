package Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Main {

	static JFrame jFrame = new JFrame();

	DatagramSocket datagramSocket;
	ServerSocket serverSocket;
	Socket socket;
	String clientScreenSize;

	int portUDP = 12345;
	int portTCP = 12346;
	boolean connectionEstablished = false;

	InetAddress inetAddress;
	String msgFromClient = "";

	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.99");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	public Main() {

		// Creating a JFrame

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		jFrame.setSize(screenWidth, screenHeight);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setOpacity(0.05f);
		jFrame.setAlwaysOnTop(false);


		UDPConnectionValidation();
		TCPConnectionValidation();


		System.out.println("Threads Started");
//			GUIAndMouse();
		System.out.println("ENDED");

	}

	private void UDPConnectionValidation() {
		// Making the UDP Connection

	}

	private void UDPReceiving(){
		try {
			datagramSocket = new DatagramSocket(portUDP);

			do {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				datagramSocket.receive(receivePacket);
				msgFromClient = new String(receivePacket.getData(), 0, receivePacket.getLength());
				System.out.println("Received from client: " + msgFromClient);
			} while (!msgFromClient.equals("Got it"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void UDPSending(){
		try {
			datagramSocket = new DatagramSocket(portUDP);
			String sendClient = "StartingUDP";

			do {
				byte[] sendData = sendClient.getBytes();
				DatagramPacket acknowledgmentPacket = new DatagramPacket(sendData, sendData.length,
								inetAddress, portUDP);
				datagramSocket.send(acknowledgmentPacket);

			} while (!msgFromClient.equals("Got it"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void TCPConnectionValidation() {

	}
}
