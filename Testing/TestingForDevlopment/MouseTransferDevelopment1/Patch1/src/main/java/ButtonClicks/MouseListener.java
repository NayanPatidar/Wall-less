package ButtonClicks;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;

public class MouseListener {
    public static void main(String[] args) throws AWTException {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        boolean breakOrNot = false;
        Robot robot = new Robot();


                switch (input){
                    case 1 :
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2 :
                        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                        break;
                    case 3:
                        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                        break;
                }

    }
}
