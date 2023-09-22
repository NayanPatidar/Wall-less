package TAB;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MouseListener {
	JFrame jFrame ;
	JTextField textField;

	// Disable focus traversal keys (e.g., Tab)

	public MouseListener(JFrame jFrame, JTextField jTextField){
		this.jFrame = jFrame;
		this.textField = jTextField;

		textField.setFocusTraversalKeysEnabled(false);
		jFrame.setSize(300, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

		textField.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					System.out.println("Tab key pressed in textField1");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		jFrame.add(textField);
	}
}
