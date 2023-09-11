package TCP;

import java.awt.*;
import java.io.*;
import java.net.*;

public class TCPClient {
	public static void main(String[] args) throws IOException, AWTException {

		Socket socket = new Socket("10.200.233.99", 12345);

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Robot robot = new Robot();
		while (true) {

			String message = in.readLine();
			System.out.println("Client received: " + message);

			String[] position = message.split(" ");
			robot.mouseMove(Integer.parseInt(position[0]), Integer.parseInt(position[1]));

		}
	}
}
