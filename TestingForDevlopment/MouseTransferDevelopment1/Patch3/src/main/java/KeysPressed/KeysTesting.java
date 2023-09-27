package KeysPressed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class KeysTesting implements  Runnable{
	public KeysTesting(JFrame jFrame) {
		jFrame.setVisible(true);
	}

	@Override
	public void run() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
			if (e.getKeyCode() == KeyEvent.VK_TAB) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					System.out.println("Tab key pressed");
				} else if (e .getID() == KeyEvent.KEY_RELEASED) {
					System.out.println("Tab key released");
				}
			}
			return false;  // Let other components handle the event as well
		});
	}
}
