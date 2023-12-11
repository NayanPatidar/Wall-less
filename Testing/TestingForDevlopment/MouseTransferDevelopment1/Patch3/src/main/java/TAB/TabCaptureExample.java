package TAB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TabCaptureExample {
	private static boolean tabPressed = false;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tab Capture Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,40));

		// Create a dummy component to request focus and capture key events
//		JPanel panel = new JPanel();


		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
			if (e.getKeyCode() == KeyEvent.VK_TAB) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					tabPressed = true;
					System.out.println("Tab key pressed");
				} else if (e.getID() == KeyEvent.KEY_RELEASED) {
					tabPressed = false;
					System.out.println("Tab key released");
				}
			}
			return false;  // Let other components handle the event as well
		});

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};

		frame.setVisible(true);
		frame.addKeyListener(keyListener);
//		panel.requestFocus();  // Request focus for the panel to capture key events
	}
}
