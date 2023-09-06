package Client;

import Server.MouseClicks;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.lang.Thread;

public class ClientMouse {
	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	int portTCP;
	int portUDP;

	public ClientMouse(DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket, int portTCP, int portUDP) {
		this.datagramSocket = datagramSocket;
		this.clientSocket = clientSocket;
		this.inetAddress = inetAddress;
		this.portTCP = portTCP;
		this.portUDP = portUDP;
		Operator();
	}

	private void Operator() {
		Thread receiveCoordinates = new Thread(new ReceivingCoordinates(datagramSocket, inetAddress, clientSocket));
		Thread positionCheck = new Thread(new PositionCheck(datagramSocket, inetAddress, clientSocket));

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
