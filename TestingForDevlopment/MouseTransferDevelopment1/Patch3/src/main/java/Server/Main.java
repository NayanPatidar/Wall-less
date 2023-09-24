package Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Main {

	static JFrame jFrame = new JFrame();
	JTextField jTextField = new JTextField();

	DatagramSocket datagramSocket;
	String clientScreenSize;
	int portUDP = 12345;

	InetAddress inetAddress;
	String msgFromClient = "";

	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.107");
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
		jFrame.setAlwaysOnTop(true);
		jFrame.setBackground(new Color(0,0,0,3));
		jFrame.setType(Window.Type.UTILITY);

		jTextField.setBackground(new Color(0,0,0,3));
		jTextField.setSize(50,50);
		jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));


		UDPConnectionValidation();

		System.out.println("Program Started");
		GUIAndMouse();
		System.out.println("ENDED");

	}

	private void GUIAndMouse() {
		new GUI( jFrame, jTextField, inetAddress, datagramSocket, portUDP, clientScreenSize);
	}

	private void UDPConnectionValidation() {
		// Making the UDP Connection
		try {
			datagramSocket = new DatagramSocket(portUDP);
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		Thread senderThread = new Thread(() -> {
			try {
				System.out.println("Starting senderThread");
				String sendClient = "StartingUDP";

				do {
					byte[] sendData = sendClient.getBytes();
					DatagramPacket acknowledgmentPacket = new DatagramPacket(sendData, sendData.length,
									inetAddress, portUDP);
					datagramSocket.send(acknowledgmentPacket);

					TimeUnit.SECONDS.sleep(2);
					System.out.println("Sent");

				} while (!msgFromClient.equals("Got it"));
				System.out.println("Exiting the senderThread");

			} catch (IOException | InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		Thread receiverThread = new Thread(() -> {
			try {
				System.out.println("Starting receiverThread");
				do {
					byte[] receiveData = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					datagramSocket.receive(receivePacket);
					String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
					String[] arr = msg.split("  ");
					msgFromClient = arr[0];
					clientScreenSize = arr[1];
					System.out.println("Received from client: " + msgFromClient);
				} while (!msgFromClient.equals("Got it"));
				System.out.println("Exiting the receiverThread");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		senderThread.start();
		receiverThread.start();
		try {
			receiverThread.join();
			senderThread.join();

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}