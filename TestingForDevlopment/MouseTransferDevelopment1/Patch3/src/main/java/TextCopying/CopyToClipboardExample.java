package TextCopying;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CopyToClipboardExample {
	public static void main(String[] args) {
		String clipboardData = getClipboardData();

			//
//		if (clipboardImage != null) {
//			System.out.println("Image retrieved from the clipboard.");
//			// Add your logic to use the image (display, save, etc.)
//		} else {
//			System.out.println("No image found on the clipboard.");
//		}
	}

	private static String getClipboardData() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);


		try {
			return (String) transferable.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static BufferedImage getClipboardImage() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);

		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
			try {
				return (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}