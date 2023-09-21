package Server;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GUI implements Runnable {
	DatagramSocket datagramSocket;
	ServerSocket serverSocket;
	Socket socket;
	JFrame jFrame;
	SharedData sharedData;
	InetAddress inetAddress;
	int portUDP ;
	int portTCP;
	String clientScreenSize;
	JWindow jWindow;
	JPanel panel;

	public GUI(JWindow jWindow, JPanel panel,JFrame jFrame, SharedData sharedData, InetAddress inetAddress, DatagramSocket datagramSocket, ServerSocket serverSocket, int portTCP, int portUDP, String clientScreenSize) {
		this.sharedData = sharedData;
		this.jFrame = jFrame;
		this.inetAddress = inetAddress;
		this.datagramSocket = datagramSocket;
		this.portTCP = portTCP;
		this.portUDP = portUDP;
		this.serverSocket = serverSocket;
		this.clientScreenSize = clientScreenSize;
		this.jWindow = jWindow;
		this.panel = panel;
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
				Image blankImage = Toolkit.getDefaultToolkit().createImage(new byte[0]);
				Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankImage, new Point(0, 0), "blankCursor");
				jWindow.setCursor(blankCursor);
				new CoordinatesSending( datagramSocket, inetAddress, portUDP, portTCP, clientScreenSize);

			} else if (cursor.getX() >= 5 && (sharedData.getForGui() == 0)) {
				System.out.println("Entering Screen");
				jWindow.dispose();
				sharedData.setForGui(1);
			}
		}
	}
}