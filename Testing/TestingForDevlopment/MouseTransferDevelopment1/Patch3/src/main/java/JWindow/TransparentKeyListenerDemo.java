package JWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class TransparentKeyListenerDemo {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {

			JFrame jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setUndecorated(true);
			jFrame.setVisible(true);

			JWindow window = new JWindow(jFrame);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension dimension = toolkit.getScreenSize();
			window.setSize(dimension.width, dimension.height);
//			window.setAlwaysOnTop(true);
			window.setBackground(new Color(0, 0, 0, 128));  // Semi-transparent background

//			// Add a JPanel to the JWindow to capture key events
			JPanel panel = new JPanel();
			panel.setFocusable(true);
			panel.setBackground(new Color(0, 0, 0, 128));
			panel.setFocusable(true);
			panel.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					System.out.println("Key Typed: " + keyChar);
				}

				@Override
				public void keyPressed(KeyEvent e) {
					int keyCode = e.getKeyCode();
					System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyCode));
				}

				@Override
				public void keyReleased(KeyEvent e) {
					int keyCode = e.getKeyCode();
					System.out.println("Key Released: " + KeyEvent.getKeyText(keyCode));
				}
			});
//
			window.add(panel);
//			window.setLocationRelativeTo(null);
			window.setVisible(true);

			Timer timer = new Timer(10000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					window.dispose();
					System.out.println("JWindow disposed after 10 seconds.");
				}
			});
			timer.setRepeats(false); // Set to false to execute only once
			timer.start();



		});
	}
}
