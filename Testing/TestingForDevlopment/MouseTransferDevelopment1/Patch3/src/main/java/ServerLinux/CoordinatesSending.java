package ServerLinux;

import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class CoordinatesSending {
	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	int portUDP;
	boolean stop = false;
	String clientScreenSize = " ";
	int ClientWidth;
	int ClientHeight;
	int ServerWidth;
	int ServerHeight;
	int loopNumX = 1;
	int loopNumY = 1;
	String side = "";

	CoordinatesSending(String OS, String side , DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP, String clientScreenSize){
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.portUDP = portUDP;
		this.clientScreenSize = clientScreenSize;
		this.side = side;

		sendingCoordinates();
	}


	public void sendingCoordinates(){

		String[] clientScreen = clientScreenSize.split(" ");
		ClientHeight = Integer.parseInt(clientScreen[0]);
		ClientWidth = Integer.parseInt(clientScreen[1]);
		System.out.println("Starting to send the position is running !!");

		Point First = MouseInfo.getPointerInfo().getLocation();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		ServerHeight = dimension.height;
		ServerWidth = dimension.width;
		if (Objects.equals(side, "Left")) {
			robot.mouseMove(dimension.width - 3, First.y);
		} else if (Objects.equals(side, "Right")) {
			robot.mouseMove(33, First.y);
		}

		while (!stop){
			Point cursorInfo = MouseInfo.getPointerInfo().getLocation();

			if (Objects.equals(side, "Left")) {
				int X = gettingXLeft(cursorInfo.x, cursorInfo.y);
				int Y = gettingYLeft(cursorInfo.x, cursorInfo.y);
				String msg = "C:" + X + " " + Y;
				byte[] sendData = msg.getBytes();

				DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
				try {
					datagramSocket.send(packet);
					if(X > ClientWidth-2 && Objects.equals(side, "Left")){
						stop = true;
						robot.mouseMove(6,Y);
						break;
					}
					Thread.sleep(3);
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public int gettingXLeft(int x, int y){
		if (loopNumX == 1){
			int msg = x-(ServerWidth-ClientWidth);
			if(msg <= 0){
				robot.mouseMove((ServerWidth-ClientWidth)+1, y);
			}
			// As my Linux is bigger than the Windows
//			if (x < 30){
//				loopNumX = 2;
//				System.out.println("Loop 2 Initialized");
//				robot.mouseMove(60, y);
//			}
			return msg;
		} else if (loopNumX == 2){
			System.out.println("Loop 2");
			if (x > (60) ){
				loopNumX = 1;
				robot.mouseMove(1, y);
			}
			return x-30;
		}
		return 0;
	}

	public int gettingYLeft(int x, int y){
		if (loopNumY == 1){
			int msg = y-(ServerHeight-ClientHeight);
			if (0 > msg){
				robot.mouseMove(x, (ServerHeight-ClientHeight) + 1);

			}
			return msg;
		} else if (loopNumY == 2) {
			if (y < ServerHeight-(ClientHeight-ServerHeight)){
				robot.mouseMove(x, ServerHeight-2);
				loopNumY = 1;
			}
			return y+(ClientHeight-ServerHeight);
		}
		return 0;
	}

	public int gettingXRight(int x, int y){
		if (loopNumX == 1) {
			if (x > ServerWidth - 2){
				System.out.println("Loop 2");
				loopNumX = 2;
				robot.mouseMove(ServerWidth - (ClientWidth - ServerWidth) , y);
			}
			return x;

		} else if (loopNumX == 2){
			int msg = x + ((ClientWidth - ServerWidth));
			if (x < (ServerWidth - (ClientWidth - ServerWidth)) ){
				loopNumX = 1;
				robot.mouseMove(ServerWidth - 2, y);
			}
			return msg;
		}
		return 0;
	}

	public int gettingYRight(int x, int y){
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

	/*
	if (loopNumX == 1) {
			int msg = x+(ClientWidth-ServerWidth);
			if (x < 31){
				loopNumX = 2;
				robot.mouseMove(ClientWidth - ServerWidth + 30, y);
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
	 */
}