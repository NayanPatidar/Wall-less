package MouseListenerTesting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseListenerExample {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Mouse Listener Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel();

			MouseAdapter mouseAdapter = new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e){
					if (e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("Left Button Pressed");

					} else if (e.getButton() == MouseEvent.BUTTON2) {
						System.out.println("Middle Button Pressed");

					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("Right Button Pressed");

					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// Invoked when a mouse button is released
					int button = e.getButton();
					if (e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("Left Button Released");

					} else if (e.getButton() == MouseEvent.BUTTON2) {
						System.out.println("Middle Button Released");

					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("Right Button Released");

					}
				}
			};

			panel.addMouseListener(mouseAdapter);

			frame.add(panel);
			frame.setSize(300, 200);
			frame.setVisible(true);
		});
	}
}
