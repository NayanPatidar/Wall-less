package TAB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TabCaptureExample {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setSize(500,500);
		jFrame.setUndecorated(true);
		jFrame.setOpacity(0.04f);
//		jFrame.setBackground(new Color(0, 0, 0, 4));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(0, 0, 0, 4));
		JTextField jTextField = new JTextField();
		jFrame.add(jTextField);
		jFrame.setVisible(true);
		MouseListener mouseListener = new MouseListener(jFrame, jTextField);

	}
}