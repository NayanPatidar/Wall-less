package Server;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class GUI {
	DatagramSocket datagramSocket;
	JFrame jFrame;
	JTextField jTextField;
	InetAddress inetAddress;
	int portUDP ;
	String clientScreenSize;
	EventListener eventListener;
	String side;
	int val = 0;

	public GUI(String side, JFrame jFrame, JTextField jTextField, InetAddress inetAddress, DatagramSocket datagramSocket, int portUDP, String clientScreenSize) {
		this.jFrame = jFrame;
		this.jTextField = jTextField;
		this.inetAddress = inetAddress;
		this.datagramSocket = datagramSocket;
		this.portUDP = portUDP;
		this.clientScreenSize = clientScreenSize;
		this.side = side;

		Start();
	}


	public void Start() {
		System.out.println("Screen Sharing started !!");

		while (true){
			Point cursor = MouseInfo.getPointerInfo().getLocation();

			if (cursor.getX() < 5 && (val == 0)) {
				System.out.println("Leaving Screen");
				System.out.println("Calling Keyboard Functionality");
				jFrame.setVisible(true);
				Image blankImage = Toolkit.getDefaultToolkit().createImage(new byte[0]);
				Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankImage, new Point(0, 0), "blankCursor");
				jFrame.setCursor(blankCursor);
				SwingUtilities.invokeLater(() -> {
					eventListener = new EventListener(jFrame, datagramSocket, inetAddress, portUDP);
				});
				new CoordinatesSending(side, datagramSocket, inetAddress, portUDP, clientScreenSize);
				val++;
			}else if (cursor.getX() >= 5 && (val == 1)) {
				System.out.println("Entering Screen");
				jFrame.dispose();
				eventListener.removeEventListeners();
				val--;
			}

			try {
				Thread.sleep(35);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}