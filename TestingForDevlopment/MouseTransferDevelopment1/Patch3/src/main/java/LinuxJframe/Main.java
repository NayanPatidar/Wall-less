package LinuxJframe;

import com.sun.jna.Pointer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws AWTException {
        JFrame jFrame = new JFrame();
        // Assuming you've set up the necessary dependencies and imported the required libraries.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

// Assume the GNOME top bar is 30 pixels high (you may need to adjust this value based on your system).
        int gnomeTopBarHeight = 30;

        int screenHeight = screenSize.height;
        int actualTaskbarHeight = screenHeight - gnomeTopBarHeight;
        System.out.println(actualTaskbarHeight);
// Subtract GNOME top bar height to get the actual taskbar height.
        Robot robot = new Robot();

        while (true){

            Point cursorInfo = MouseInfo.getPointerInfo().getLocation();
            int x = cursorInfo.x;
            int y = cursorInfo.y;
            System.out.println(x + " " + y);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
