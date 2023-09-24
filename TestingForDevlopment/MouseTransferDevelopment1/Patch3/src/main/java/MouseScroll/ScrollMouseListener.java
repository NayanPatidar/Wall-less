package MouseScroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollMouseListener implements MouseWheelListener {
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		int notches = e.getScrollType();
		System.out.println("Mouse Wheel Moved " + notches + "notches.");
	}

	public static void main(String[] args) throws AWTException {
		JFrame jFrame = new JFrame();
		jFrame.setSize(500,500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jPanel = new JPanel();
		jFrame.add(jPanel);

//		ScrollMouseListener listener = new ScrollMouseListener();
//		jPanel.addMouseWheelListener(listener);
//		jFrame.setVisible(true);

		Robot robot = new Robot();
		robot.mouseWheel(-1);

	}
}
