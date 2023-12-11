package KeybaordListener;

import javax.swing.*;
import java.awt.event.KeyListener;

public class Start {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setSize(500,500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jPanel = new JPanel();
		jFrame.add(jPanel);
		KeyListener keyListener = new KeyboardListener();

		jPanel.addKeyListener(keyListener);
		jPanel.setFocusable(true);
		jPanel.requestFocusInWindow();
		jFrame.setVisible(true);
	}
}
