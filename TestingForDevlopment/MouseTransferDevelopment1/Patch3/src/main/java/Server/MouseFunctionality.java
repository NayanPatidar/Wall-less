package Server;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MouseFunctionality  {

	static private JWindow jWindow;
	private final MouseAdapter mouseAdapter;

	public MouseFunctionality(JWindow jWindow, DatagramSocket datagramSocket, InetAddress inetAddress,int portUDP) {
		this.jWindow = jWindow;
		String leftClickPressed = "B:3";
		String middleClickPressed = "B:2";
		String rightClickPressed = "B:1";
		byte[] bufferLeftPressed = leftClickPressed.getBytes();
		byte[] bufferRightPressed = rightClickPressed.getBytes();
		byte[] bufferMiddlePressed = middleClickPressed.getBytes();
		String leftClickReleased = "B:3'";
		String middleClickReleased = "B:2'";
		String rightClickReleased = "B:1'";
		byte[] bufferLeftReleased = leftClickReleased.getBytes();
		byte[] bufferRightReleased = rightClickReleased.getBytes();
		byte[] bufferMiddleReleased = middleClickReleased.getBytes();
		DatagramPacket packetLeftReleased = new DatagramPacket(bufferLeftReleased, bufferLeftReleased.length, inetAddress, portUDP);
		DatagramPacket packetMiddleReleased = new DatagramPacket(bufferMiddleReleased, bufferMiddleReleased.length, inetAddress, portUDP);
		DatagramPacket packetRightReleased = new DatagramPacket(bufferRightReleased, bufferRightReleased.length, inetAddress, portUDP);
		DatagramPacket packetLeftPressed = new DatagramPacket(bufferLeftPressed, bufferLeftPressed.length, inetAddress, portUDP);
		DatagramPacket packetMiddlePressed = new DatagramPacket(bufferMiddlePressed, bufferMiddlePressed.length, inetAddress, portUDP);
		DatagramPacket packetRightPressed = new DatagramPacket(bufferRightPressed, bufferRightPressed.length, inetAddress, portUDP);


		mouseAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e){
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("Left Button Pressed");

					try {
						datagramSocket.send(packetLeftPressed);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}

				} else if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Middle Button Pressed");

					try {
						datagramSocket.send(packetMiddlePressed);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}

				} else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Right Button Pressed");

					try {
						datagramSocket.send(packetRightPressed);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("Left Button Released");

					try {
						datagramSocket.send(packetLeftReleased);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}

				} else if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Middle Button Released");

					try {
						datagramSocket.send(packetMiddleReleased);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}

				} else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Right Button Released");

					try {
						datagramSocket.send(packetRightReleased);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}

				}
			}
		};
		jWindow.addMouseListener(mouseAdapter);
	}

	public void disposeMouseListener() {
		jWindow.removeMouseListener(mouseAdapter);
	}

}
