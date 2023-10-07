package TextCopying;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		String clipboardData = getClipboardData();

		try (Socket socket = new Socket("10.200.233.31", 12346);
		     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			out.println(clipboardData);
			System.out.println("Data sent to client: " + clipboardData);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getClipboardData() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);
		try {
			return "T:'" + (String) transferable.getTransferData(DataFlavor.stringFlavor) + "'";
		} catch (UnsupportedFlavorException | IOException e) {
			throw new RuntimeException(e);
		}
    }
}