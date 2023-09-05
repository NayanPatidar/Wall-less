package Server;

import com.sun.security.ntlm.Server;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	static JFrame jFrame = new JFrame();

	// All the sockets - UDP and TCP
	DatagramSocket datagramSocket;
	ServerSocket serverSocket;
	Socket socket;

	// Client Scree Size
	String clientScreenSize;

	// Ports and Address
	int portUDP = 12345;
	int portTCP = 12346;
	InetAddress inetAddress;

	// To verify if the connection is established or not
	boolean connectionEstablished = false;

	public Main(){

		// Creating JFrame
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		jFrame.setSize(screenWidth, screenHeight);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setOpacity(0.05f);

// Initialize sockets
		try {
			datagramSocket = new DatagramSocket(portUDP);
			serverSocket = new ServerSocket(portTCP);
			socket = serverSocket.accept();
			inetAddress = InetAddress.getByName("10.200.233.131");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Connection connection = new Connection(datagramSocket, serverSocket, socket, inetAddress, portUDP, portTCP);

		if (connection.UDPDone && connection.TCPDone) {
			System.out.println("Threads Started");

		}
		System.out.println("ENDED");
	}
}
