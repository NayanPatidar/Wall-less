package TCP;

import java.awt.*;
import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args) throws IOException {
		// Create a server socket on port 12345
		ServerSocket serverSocket = new ServerSocket(12345);
		System.out.println("Server is listening for incoming connections...");

		Socket clientSocket = serverSocket.accept();
		System.out.println("Client connected: " + clientSocket.getInetAddress());

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


		while (true) {
			Point cursorInfo = MouseInfo.getPointerInfo().getLocation();
			int x = cursorInfo.x;
			int y = cursorInfo.y;

			String message = x + " " + y;
			out.println(message);

			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				System.out.println("Error Received: " + e.getLocalizedMessage());
			}
		}
	}
}
