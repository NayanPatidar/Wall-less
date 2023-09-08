package Server;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.DatagramSocket;

public class MouseFunctionality  {

	static private JFrame jFrame;
	private final MouseAdapter mouseAdapter;

	public MouseFunctionality(JFrame frame, DatagramSocket datagramSocket) {
		jFrame = frame;

		mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("Left Button");
				} else if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Middle Button");
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Right Button");
				}
			}
		};
		jFrame.addMouseListener(mouseAdapter);
	}

	public void disposeMouseListener() {
		jFrame.removeMouseListener(mouseAdapter);
	}

}
