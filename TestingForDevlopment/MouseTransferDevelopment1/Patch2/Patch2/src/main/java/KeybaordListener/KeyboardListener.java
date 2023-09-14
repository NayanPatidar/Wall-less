package KeybaordListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		System.out.println(keyChar);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		System.out.println("Key Pressed has the code : " + keyCode);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		System.out.println("Key Released has the code : " + keyCode);
	}
}
