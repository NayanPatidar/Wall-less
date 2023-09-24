package AltTabPreventionExample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class AltTabListener  {
	KeyListener keyListener;

	public AltTabListener() {
		JFrame jFrame = new JFrame();
		jFrame.setSize(500,500);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setOpacity(0.1f);
		keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(f -> {
					if (f.getKeyCode() == KeyEvent.VK_TAB ) {
						if (f.getID() == KeyEvent.KEY_PRESSED ) {
							System.out.println("Tab key pressed");
						} else if (f.getID() == KeyEvent.KEY_RELEASED ) {
							System.out.println("Tab key released");
						}
					}
					return false;  // Let other components handle the event as well
				});

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};
		jFrame.addKeyListener(keyListener);
		jFrame.setVisible(true);

	}

	public static void main(String[] args) {
		AltTabListener altTabListener = new AltTabListener();
	}
}
