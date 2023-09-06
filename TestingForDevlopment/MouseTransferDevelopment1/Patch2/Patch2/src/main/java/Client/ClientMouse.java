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

	public ClientMouse(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket, int portTCP, int portUDP, OutputStream outputStream) {
		this.datagramSocket = datagramSocket;
		this.clientSocket = clientSocket;
		this.inetAddress = inetAddress;
		this.portTCP = portTCP;
		this.portUDP = portUDP;
		this.outputStream = outputStream;
		Operator();
	}

	private void Operator() {
		Thread receiveCoordinates = new Thread(new ReceivingCoordinates(datagramSocket, inetAddress, clientSocket));
		Thread positionCheck = new Thread(new PositionCheck(datagramSocket, inetAddress, clientSocket, outputStream));

		receiveCoordinates.start();
		positionCheck.start();

		try {
			receiveCoordinates.join();
			positionCheck.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


}
