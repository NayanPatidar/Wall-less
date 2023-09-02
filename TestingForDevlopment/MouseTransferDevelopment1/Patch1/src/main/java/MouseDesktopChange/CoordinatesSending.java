package MouseDesktopChange;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class CoordinatesSending {
	Socket socket;
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	int port;
	int portTCP;
	boolean stop = false;
	String message = "ok";

					CoordinatesSending(Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int port, int portTCP){
		this.socket = socket;
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.port = port;
		this.portTCP = portTCP;
		operator();
	}

	private void operator() {
		Thread notification = new Thread(() -> {
			System.out.println("Thread to get notified is running !!");
			notifyingToStop();
		});

		Thread sendingPosition = new Thread(() -> {
			System.out.println("Thread to sending the position is running !!");
			sendingCoordinates();
		});

		notification.start();
		sendingPosition.start();

		try {
			notification.join();
			sendingPosition.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendingCoordinates(){

		while (!stop){
			Point cursorInfo = MouseInfo.getPointerInfo().getLocation();
			int x = cursorInfo.x;
			int y = cursorInfo.y;
			String msg = x + " " + y;
			byte[] sendData = msg.getBytes();

			DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, port);
			try {
				datagramSocket.send(packet);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void notifyingToStop() {

		while (true) {
			try {
//				System.out.println("Client connected: " + socket.getInetAddress());
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				try {
					 message = reader.readLine();

					while ((message = reader.readLine()) != null) {
						if (message.equalsIgnoreCase("stop")) {
							// If the "stop" message is received, exit the loop
							System.out.println("Received 'stop' message. Stopping...");
							break;
						}
					}

				} catch (IOException e) {
						System.err.println("Error reading message from client: " + e.getMessage());
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
}
