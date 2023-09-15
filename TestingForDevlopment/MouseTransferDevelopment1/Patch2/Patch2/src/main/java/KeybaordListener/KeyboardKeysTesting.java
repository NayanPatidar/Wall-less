package KeybaordListener;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardKeysTesting {
	public static void main(String[] args) throws AWTException {
		Robot robot = new Robot();
		int val = 46;
		switch (val){
			case 46: // Delete key
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				System.out.println("Pressed Delete key");
				break;
		}
	}
}
