package MouseDesktopChange;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;

public class GUI implements Runnable {
    DatagramSocket datagramSocket;
    JFrame jFrame;
    SharedData sharedData;

    public GUI(JFrame jFrame, SharedData sharedData, DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
        this.sharedData = sharedData;
        this.jFrame = jFrame;
    }

    @Override
    public void run() {
        while (true){
            Point mousePosition = MouseInfo.getPointerInfo().getLocation();

            // If the mouse position is less than 80 then starting the share coordinates
            // and bringing the transparent frame on the pc

            if (mousePosition.getX() < 80 && (sharedData.getForGui() == 1)){
                System.out.println("Leaving Screen");
                jFrame.setVisible(true);
                sharedData.setForGui(0);

            } else if (mousePosition.getX() >= 80 && (sharedData.getForGui() == 0)) {
                System.out.println("Entered Screen");
                jFrame.dispose();
                sharedData.setForGui(1);
            }
        }
    }
}
