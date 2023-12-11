package KeybaordListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	private boolean altPressed = false;
	private boolean ctrlPressed = false;
	private boolean tPressed = false;

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ALT) {
			altPressed = true;
		} else if (keyCode == KeyEvent.VK_CONTROL) {
			ctrlPressed = true;
		} else if (keyCode == KeyEvent.VK_T) {
			tPressed = true;
		}

		checkKeys();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ALT) {
			altPressed = false;
		} else if (keyCode == KeyEvent.VK_CONTROL) {
			ctrlPressed = false;
		} else if (keyCode == KeyEvent.VK_T) {
			tPressed = false;
		}

		checkKeys();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not needed for this example
	}

	private void checkKeys() {
		if (altPressed && ctrlPressed && tPressed) {
			// Alt + Ctrl + T is pressed
			System.out.println("Alt + Ctrl + T is pressed");
			// Reset the flags
			altPressed = false;
			ctrlPressed = false;
			tPressed = false;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Key Listener Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);

		KeyboardListener keyListener = new KeyboardListener();
		frame.addKeyListener(keyListener);

		// Add a label to the frame to show instructions
		JLabel label = new JLabel("Press Alt + Ctrl + T");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(label, BorderLayout.CENTER);

		frame.setVisible(true);
	}
}
