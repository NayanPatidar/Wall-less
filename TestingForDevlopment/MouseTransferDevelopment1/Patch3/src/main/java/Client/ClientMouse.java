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
	int portTCP;
	int portUDP;

	public ClientMouse(DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.portUDP = portUDP;
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
