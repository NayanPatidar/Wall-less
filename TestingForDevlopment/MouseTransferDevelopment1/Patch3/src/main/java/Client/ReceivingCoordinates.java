package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

public class ReceivingCoordinates implements Runnable{

	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	String side;

	private JFrame dummyFrame;
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}


	public ReceivingCoordinates(String side, DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.clientSocket = clientSocket;
		this.side = side;

		// Create a dummy invisible frame
		dummyFrame = new JFrame();
		dummyFrame.setUndecorated(true);
		dummyFrame.setSize(1, 1);
		dummyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dummyFrame.setAlwaysOnTop(true);

		dummyFrame.setVisible(true);

	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize().getSize();

		byte[] buffer = new byte[1024];
		while (true){
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				datagramSocket.receive(packet);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			String receivedMsg = new String(packet.getData(), 0, packet.getLength());
			if (receivedMsg.startsWith("C:")) {
				String msg = receivedMsg.substring(2);
				String[] parts = msg.split(" ");
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				robot.mouseMove(x, y);

				if (x >= dimension.width-2 && Objects.equals(side, "Left")){
					System.out.println("Left Screen");
					robot.mouseMove(400,toolkit.getScreenSize().height);
				} else if (x < 2 && Objects.equals(side, "Right")){
					System.out.println("Left Screen");
					robot.mouseMove(400,toolkit.getScreenSize().height);
				}
			}
		}
	}
}
