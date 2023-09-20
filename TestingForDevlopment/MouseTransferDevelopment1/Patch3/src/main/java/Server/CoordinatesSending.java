package Server;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

	public CoordinatesSending(DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP, String clientScreenSize) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.portUDP = portUDP;
		this.clientScreenSize = clientScreenSize;

		sendCoordinates();
	}

	private void sendCoordinates() {
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
}
