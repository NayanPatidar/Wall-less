package ClientCPP;

import java.awt.*;
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

			while (true) {
				Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
				int x = (int) mouseLocation.getX();
				int y = (int) mouseLocation.getY();
				String message = x + " " + y;
				out.write(message.getBytes());
				Thread.sleep(2);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}
}
