package TextCopying;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyToClipboardExample {

	public static void main(String[] args) {
		String textToCopy = "This is the text to copy.";

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();

		StringSelection stringSelection = new StringSelection(textToCopy);
		clipboard.setContents(stringSelection, null);

		System.out.println("Text has been copied to the clipboard.");
	}
}