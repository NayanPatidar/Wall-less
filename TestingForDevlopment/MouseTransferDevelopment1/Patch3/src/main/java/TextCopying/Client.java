package TextCopying;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.*;

public class Client {
	private static final int PORT = 12346;
	private static final int BUFFER_SIZE = 1024; // Adjust buffer size as needed



	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Waiting for data from server...");
			Socket clientSocket = serverSocket.accept();
			receiveAndSetClipboardData(clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void receiveAndSetClipboardData(Socket clientSocket) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead;

		InputStream inputStream = clientSocket.getInputStream();
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			stringBuilder.append(new String(buffer, 0, bytesRead));
		}

		System.out.println(stringBuilder);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(stringBuilder.toString());
		clipboard.setContents(selection, null);
	}
}
