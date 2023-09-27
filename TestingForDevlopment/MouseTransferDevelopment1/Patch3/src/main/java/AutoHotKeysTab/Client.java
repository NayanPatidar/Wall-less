package AutoHotKeysTab;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {
	public static void main(String[] args) throws IOException {
				DatagramSocket socket = new DatagramSocket(5000);
				while (true) {
					byte[] buffer = new byte[1024];
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					socket.receive(packet);

					String message = new String(packet.getData(), 0, packet.getLength());
					System.out.println("Received message: " + message);
				}
			}
		}
