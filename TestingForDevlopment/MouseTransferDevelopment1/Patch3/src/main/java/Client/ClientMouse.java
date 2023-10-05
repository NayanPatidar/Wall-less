package Client;

import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.lang.Thread;

public class ClientMouse {
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	OutputStream outputStream;
	String side;
	int portUDP;

	public ClientMouse(String side, DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket, int portUDP, OutputStream outputStream) {
		this.datagramSocket = datagramSocket;
		this.clientSocket = clientSocket;
		this.inetAddress = inetAddress;
		this.portUDP = portUDP;
		this.outputStream = outputStream;
		this.side = side;
		Operator();
	}

	private void Operator() {
		Thread receiveCoordinates = new Thread(new ReceivingCoordinates(side, datagramSocket, inetAddress, clientSocket));

		receiveCoordinates.start();

		try {
			receiveCoordinates.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
