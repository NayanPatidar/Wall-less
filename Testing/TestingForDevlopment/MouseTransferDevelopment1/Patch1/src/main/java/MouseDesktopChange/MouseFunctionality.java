package MouseDesktopChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseFunctionality  {

	static private JFrame jFrame;
	private MouseAdapter mouseAdapter;

	public MouseFunctionality(JFrame frame) {
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