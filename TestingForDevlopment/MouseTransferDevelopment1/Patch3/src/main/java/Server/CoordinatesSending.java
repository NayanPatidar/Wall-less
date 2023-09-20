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

		Thread sendingPosition = new Thread(() -> {
			String[] clientScreen = clientScreenSize.split(" ");
			ClientHeight = Integer.parseInt(clientScreen[0]);
			ClientWidth = Integer.parseInt(clientScreen[1]);
			System.out.println("Thread to sending the position is running !!");
			sendingCoordinates();
		});
		sendingPosition.start();

		try {
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
		robot.mouseMove(dimension.width - 3, First.y);

		while (!stop){
			Point cursorInfo = MouseInfo.getPointerInfo().getLocation();
			int x = cursorInfo.x;
			int y = cursorInfo.y;
			int X = gettingX(x, y);
			int Y = gettingY(x, y);

			String msg = "C:" + X + " " + Y;
			byte[] sendData = msg.getBytes();

			DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
			try {
				datagramSocket.send(packet);
				if(X > ClientWidth-2 ){
					stop = true;
					robot.mouseMove(6,Y);
					break;
				}
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
				robot.mouseMove(x, ServerHeight-(ClientHeight-ServerHeight));
				loopNumY = 2;
			}
			return y;
		} else if (loopNumY == 2) {
			if (y < ServerHeight-(ClientHeight-ServerHeight)){
				robot.mouseMove(x, ServerHeight-2);
				loopNumY = 1;
			}
			return y+(ClientHeight-ServerHeight);
		}
		return 0;
	}



//		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			System.out.println(socket.isConnected());
//			try {
//				System.out.println(socket.getInetAddress());
//				System.out.println("Waiting for msg");
//
//				while (!stop) {
//					System.out.println(socket.isInputShutdown());
//
//					InputStream inputStream = socket.getInputStream();
//					byte[] buffer = new byte[1024];
//					int bytesRead = inputStream.read(buffer);
//					String clientMessage = new String(buffer, 0, bytesRead);
//					System.out.println("Received from client: " + clientMessage);
//
//					if (clientMessage.equals("stop")){
//						System.out.println("Stopping receiving ...");
//						stop = true;
//					}
//
//
//				}
//			} catch (IOException e) {
//				System.err.println("Error reading message from client: " + e.getMessage());
//			}
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
}