package MouseDesktopChange;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class CoordinatesSending {
	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

	Socket socket;
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	int port;
	int portTCP;
	boolean stop = false;
	String message = "ok";
	String clientScreenSize = " ";
	int ClientWidth;
	int ClientHeight;
	int ServerWidth;
	int ServerHeight;
	int loopNum = 1;

	CoordinatesSending(Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int port, int portTCP, String clientScreenSize){
		this.socket = socket;
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.port = port;
		this.portTCP = portTCP;
		this.clientScreenSize = clientScreenSize;
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
		Point First = MouseInfo.getPointerInfo().getLocation();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		ServerHeight = dimension.height;
		ServerWidth = dimension.width;
		robot.mouseMove(dimension.width, First.y);

		while (!stop){
			Point cursorInfo = MouseInfo.getPointerInfo().getLocation();
			int x = cursorInfo.x;
			int y = cursorInfo.y;
//			System.out.println(y);
			if (loopNum == 1) {

				String msg = x+(ClientWidth-ServerWidth) + " " + y;
				byte[] sendData = msg.getBytes();

				DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, port);
				try {
					datagramSocket.send(packet);
					Thread.sleep(2);
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}

				if (x < 1){
					loopNum = 2;
					robot.mouseMove(ClientWidth - ServerWidth, y);
				}
			} else if (loopNum == 2){
				String msg = x + " " + y;
				byte[] sendData = msg.getBytes();

				DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, port);
				try {
					datagramSocket.send(packet);
					Thread.sleep(2);
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}

				if (x > (ClientWidth-ServerWidth) ){
//					System.out.println("Here");
					loopNum = 1;
					System.out.println(x + " " + y);
					robot.mouseMove(1, y);
				}

			}
		}
	}

	public void notifyingToStop() {
		String[] clientScreen = clientScreenSize.split(" ");
		ClientHeight = Integer.parseInt(clientScreen[0]);
		ClientWidth = Integer.parseInt(clientScreen[1]);
		System.out.println("Client Height is : " + ClientHeight);
		System.out.println("Client Width is :" + ClientWidth);

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
