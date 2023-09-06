package Server;

import java.awt.*;
import java.io.IOException;
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
	int portUDP;
	int portTCP;
	boolean stop = false;
	String message = "ok";
	String clientScreenSize = " ";
	int ClientWidth;
	int ClientHeight;
	int ServerWidth;
	int ServerHeight;
	int loopNumX = 1;
	int loopNumY = 1;

	CoordinatesSending(Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int port, int portTCP, String clientScreenSize){
		this.socket = socket;
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.portUDP = port;
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
			int X = gettingX(x, y);
			int Y = gettingY(x, y);

			String msg = X + " " + Y;
			byte[] sendData = msg.getBytes();

			DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
			try {
				datagramSocket.send(packet);
				Thread.sleep(2);
			} catch (IOException | InterruptedException e) {
				throw new RuntimeException(e);


			}
		}
	}

	public int gettingX(int x, int y){
		if (loopNumX == 1) {

			int msg = x+(ClientWidth-ServerWidth);

			if (x < 1){
				loopNumX = 2;
				robot.mouseMove(ClientWidth - ServerWidth, y);
			}
			return msg;

		} else if (loopNumX == 2){
			if (x > (ClientWidth-ServerWidth) ){
				loopNumX = 1;
				robot.mouseMove(1, y);
			}
			return x;
		}
		return 0;
	}

	public int gettingY(int x, int y){
		if (loopNumY == 1){
			if (y > ServerHeight-2){
//				System.out.println("Got it");
				robot.mouseMove(x, ServerHeight-(ClientHeight-ServerHeight));
				loopNumY = 2;
			}
			return y;
		} else if (loopNumY == 2) {
//			System.out.println("Reached");
			if (y < ServerHeight-(ClientHeight-ServerHeight)){
				robot.mouseMove(x, ServerHeight-2);
				loopNumY = 1;
			}
			return y+(ClientHeight-ServerHeight);
		}
		return 0;
	}

	public void notifyingToStop() {

	}
}