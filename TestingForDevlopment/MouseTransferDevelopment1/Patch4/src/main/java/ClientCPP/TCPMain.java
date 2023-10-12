package ClientCPP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPMain {
	static InetAddress inetAddress;

	static {
		try {
			inetAddress = InetAddress.getByName("10.200.233.107");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {

		try(Socket socket = new Socket(inetAddress, 8085);) {
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);

			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String message = "Hello, server!";
			writer.println(message);
			String receivedData = reader.readLine();
			System.out.println("Received: " + receivedData);

			writer.close();
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}
}
