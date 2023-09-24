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

		JTextField textField = new JTextField() {
			@Override
			protected void processKeyEvent(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					// Handle Tab key press
					System.out.println("Tab pressed");
				}
			}
		};

		jFrame.add(textField);
		jFrame.setVisible(true);
	}
}
