package TextCopying;

import java.awt.*;
import java.awt.datatransfer.*;

public class CopyToClipboardExample {
	public static void main(String[] args) {
		String textToCopy = "This is the text to be copied to the clipboard.";
		copyToClipboard(textToCopy);
		System.out.println("Text copied to clipboard: " + textToCopy);
	}

	private static void copyToClipboard(String text) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(text);
		clipboard.setContents(selection, null);
	}
}
