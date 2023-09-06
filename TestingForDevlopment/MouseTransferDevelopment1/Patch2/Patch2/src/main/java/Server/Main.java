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
			GUIAndMouse();
		System.out.println("ENDED");

	}

	private void GUIAndMouse() {
		SharedData sharedData = new SharedData();

		Thread threadA = new Thread(new GUI(jFrame, sharedData, socket, inetAddress, datagramSocket, serverSocket, portTCP, portUDP, clientScreenSize));
		Thread threadB = new Thread(new MouseClicks(jFrame, sharedData, socket, datagramSocket) );

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
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

	private void TCPConnectionValidation() {
		try {
			serverSocket = new ServerSocket(portTCP);
			System.out.println("TCP server is listening on port " + portTCP);
			socket = serverSocket.accept();
			System.out.println("Client connected :" + socket.getInetAddress());

			String sendingUDPMsg = "StartingTCP";
			OutputStream outputStream = socket.getOutputStream();
			byte[] sendingMsg = sendingUDPMsg.getBytes();
			outputStream.write(sendingMsg);

			InputStream inputStream = socket.getInputStream();
			byte[] buffer = new byte[1024];
			int bytesRead = inputStream.read(buffer);
			String clientMessage = new String(buffer, 0, bytesRead);
			System.out.println("Received from client: " + clientMessage);
			connectionEstablished = true;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}