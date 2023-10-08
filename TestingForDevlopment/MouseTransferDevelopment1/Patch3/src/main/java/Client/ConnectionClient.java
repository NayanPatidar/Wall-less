package Client;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class ConnectionClient {
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	boolean connectionEstablished;
	OutputStream outputStream;
	String side = "";
	{
		try {
			inetAddress = InetAddress.getByName("10.200.233.107");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	int portUDP = 12345;

	public ConnectionClient(){
		UDPConnection();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		new ClientMouse(side, datagramSocket, inetAddress, clientSocket, portUDP, outputStream);
	}

	public void UDPConnection() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	}
}
