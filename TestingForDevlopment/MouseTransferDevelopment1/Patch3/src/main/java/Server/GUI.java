package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GUI implements Runnable {
	DatagramSocket datagramSocket;
	JWindow jWindow;
	SharedData sharedData;
	InetAddress inetAddress;
	int portUDP ;
	String clientScreenSize;

	// Transparent Cursor
	BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
					cursorImage, new Point(0, 0), "TransparentCursor");

	public GUI(JWindow jWindow, SharedData sharedData, InetAddress inetAddress, DatagramSocket datagramSocket, int portUDP, String clientScreenSize) {
		this.sharedData = sharedData;
		this.jWindow = jWindow;
		this.inetAddress = inetAddress;
		this.datagramSocket = datagramSocket;
		this.portUDP = portUDP;
		this.clientScreenSize = clientScreenSize;
	}

	@Override
	public void run() {
		System.out.println("Screen Sharing started !!");

		while (true){
			Point cursor = MouseInfo.getPointerInfo().getLocation();

			if (cursor.getX() < 5 && (sharedData.getForGui() == 1)){
				System.out.println("Leaving Screen");
				jWindow.setVisible(true);
				sharedData.setForGui(0);
				jWindow.setCursor(transparentCursor);
				new CoordinatesSending( datagramSocket, inetAddress, portUDP, clientScreenSize);

			} else if (cursor.getX() >= 5 && (sharedData.getForGui() == 0)) {
				System.out.println("Entering Screen");
				jWindow.dispose();
				sharedData.setForGui(1);
			}
		}
	}
}
