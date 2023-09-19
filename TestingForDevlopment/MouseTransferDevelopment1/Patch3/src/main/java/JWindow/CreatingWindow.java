package JWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CreatingWindow {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JWindow window = new JWindow();
			window.setSize(800, 600);
			window.setBackground(new Color(0, 0, 0, 1)); // Transparent background

			// Create a transparent image for the cursor
			BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
							cursorImage, new Point(0, 0), "TransparentCursor");

			// Set the custom cursor for the JWindow
			window.setCursor(transparentCursor);

			// Add a mouse listener to close the window on mouse click
			window.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					window.dispose();
				}
			});

			window.setVisible(true);

		});
	}
}
