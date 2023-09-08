package Server;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MouseFunctionality  {

	static private JFrame jFrame;
	private final MouseAdapter mouseAdapter;

	public MouseFunctionality(JFrame frame, DatagramSocket datagramSocket, InetAddress inetAddress,int portUDP) {
		jFrame = frame;
		String leftClick = "B:3";
		String middleClick = "B:2";
		String rightClick = "B:1";
		byte[] bufferLeft = leftClick.getBytes();
		byte[] bufferRight = rightClick.getBytes();
		byte[] bufferMiddle= middleClick.getBytes();
		DatagramPacket packetLeft = new DatagramPacket(bufferLeft, bufferLeft.length, inetAddress, portUDP);
		DatagramPacket packetMiddle = new DatagramPacket(bufferMiddle, bufferMiddle.length, inetAddress, portUDP);
		DatagramPacket packetRight = new DatagramPacket(bufferRight, bufferRight.length, inetAddress, portUDP);



		mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("Left Button");
					try {
						datagramSocket.send(packetLeft);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				} else if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Middle Button");
					try {
						datagramSocket.send(packetMiddle);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Right Button");
					try {
						datagramSocket.send(packetRight);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		};
		jFrame.addMouseListener(mouseAdapter);
	}

	public void disposeMouseListener() {
		jFrame.removeMouseListener(mouseAdapter);
	}

}
