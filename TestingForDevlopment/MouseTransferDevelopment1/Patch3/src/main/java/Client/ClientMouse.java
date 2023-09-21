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
	int portUDP;

	public ClientMouse(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket, int portUDP, OutputStream outputStream) {
		this.datagramSocket = datagramSocket;
		this.clientSocket = clientSocket;
		this.inetAddress = inetAddress;
		this.portUDP = portUDP;
		this.outputStream = outputStream;
		Operator();
	}

	private void Operator() {
		Thread receiveCoordinates = new Thread(new ReceivingCoordinates(datagramSocket, inetAddress, clientSocket));

		receiveCoordinates.start();

		try {
			receiveCoordinates.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
