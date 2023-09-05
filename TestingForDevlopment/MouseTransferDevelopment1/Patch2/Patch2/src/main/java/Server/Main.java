package Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Main {

	static JFrame jFrame = new JFrame();

	DatagramSocket datagramSocket;
	ServerSocket serverSocket;
	Socket socket;
	String clientScreenSize;

	int port = 12345;
	int portTCP = 12346;
	boolean connectionEstablished = false;

	InetAddress inetAddress;

	public Main() {

		// Creating a JFrame

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		jFrame.setSize(screenWidth, screenHeight);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setOpacity(0.05f);
		jFrame.setAlwaysOnTop(false);


		UDPConnectionValidation();
		TCPConnectionValidation();


		System.out.println("Threads Started");
//			GUIAndMouse();
		System.out.println("ENDED");

	}

	private void UDPConnectionValidation() {

	}

	private void TCPConnectionValidation() {

	}

}
