package KeybaordListener;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardKeysTesting {
	public static void main(String[] args) throws AWTException {
		Robot robot = new Robot();
		int val = 44;
		switch (val){
			case 44: // Comma (,)
				System.out.println("Comma (,) Key");
				robot.keyPress(KeyEvent.VK_COMMA);
				robot.keyRelease(KeyEvent.VK_COMMA);
				break;
			case 46: // Period (.)
				System.out.println("Period (.) Key");
				robot.keyPress(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_PERIOD);
				break;
			case 47: // Slash (/)
				System.out.println("Slash (/) Key");
				robot.keyPress(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SLASH);
				break;
			case 92: // Backslash (\)
				System.out.println("Backslash (\\) Key");
				robot.keyPress(KeyEvent.VK_BACK_SLASH);
				robot.keyRelease(KeyEvent.VK_BACK_SLASH);
				break;
			case 91: // Open Bracket ([)
				System.out.println("Open Bracket ([) Key");
				robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
				robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
				break;
			case 93: // Close Bracket (])
				System.out.println("Close Bracket (]) Key");
				robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
				robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
				break;
			case 59: // Semicolon (;)
				System.out.println("Semicolon (;) Key");
				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
				break;
			case 222: // Quote (')
				System.out.println("Quote (') Key");
				robot.keyPress(KeyEvent.VK_QUOTE);
				robot.keyRelease(KeyEvent.VK_QUOTE);
				break;
		}
	}
}
