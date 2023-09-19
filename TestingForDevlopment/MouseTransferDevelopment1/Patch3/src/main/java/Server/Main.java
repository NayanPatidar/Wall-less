package Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Main {

	JWindow jWindow = new JWindow();

	DatagramSocket datagramSocket;
	Socket socket;
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

		SwingUtilities.invokeLater(() -> {

			jWindow.setSize(screenWidth, screenHeight);
			jWindow.setBackground(new Color(0, 0, 0, 1));

			UDPConnectionValidation();

			System.out.println("Threads Started");
			GUIAndMouse();
			System.out.println("ENDED");
		});


	}

	private void GUIAndMouse() {
		SharedData sharedData = new SharedData();

		Thread threadA = new Thread(new GUI(jWindow, sharedData, inetAddress, datagramSocket, portUDP, clientScreenSize));
		Thread threadB = new Thread(new MouseClicks(jWindow, sharedData, socket, datagramSocket, inetAddress, portUDP));
		Thread threadC = new Thread(new ButtonClicks(jWindow, sharedData, socket, datagramSocket, inetAddress, portUDP));


		threadA.start();
		threadB.start();
		threadC.start();

		try {
			threadA.join();
			threadB.join();
			threadC.join();
			System.out.println("Threads closed");

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
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