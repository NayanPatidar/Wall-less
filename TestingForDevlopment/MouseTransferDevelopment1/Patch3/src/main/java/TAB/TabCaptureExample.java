package TAB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TabCaptureExample {
	private static boolean tabPressed = false;
	static boolean altPress = false;
	static boolean ctrlPress = false;
	static boolean shiftPress = false;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tab Capture Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,40));

		// Create a dummy component to request focus and capture key events
//		JPanel panel = new JPanel();


//		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
//			if (e.getKeyCode() == KeyEvent.VK_TAB) {
//				if (e.getID() == KeyEvent.KEY_PRESSED && !tabPressed) {
//					tabPressed = true;
//					System.out.println("Tab key pressed");
//				} else if (e.getID() == KeyEvent.KEY_RELEASED && tabPressed) {
//					tabPressed = false;
//					System.out.println("Tab key released");
//				}
//			}
//			return false;
//		});

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if ( !shiftPress && e.getKeyCode() == 16) {
						System.out.println("Shift Pressed");
						shiftPress = true;
				}
				if ( !altPress && e.getKeyCode() == 18) {
						System.out.println("Alt Pressed");
					altPress= true;

				}
				if ( !ctrlPress && e.getKeyCode() == 17) {
						System.out.println("Control Pressed");
					ctrlPress = true;

				}
				if (e.getKeyCode() == 9 ) {
					System.out.println("Got it ");
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_SHIFT -> {
						System.out.println("Shift Released");
						shiftPress = false;
					}
					case KeyEvent.VK_ALT -> {
						System.out.println("Alt Released");
						altPress = false;
					}
					case KeyEvent.VK_CONTROL -> {
						System.out.println("Control Released");
						ctrlPress = false;
					}
				}
			}
		};
		frame.addKeyListener(keyListener);
		frame.setVisible(true);
//		panel.requestFocus();  // Request focus for the panel to capture key events
	}
}
