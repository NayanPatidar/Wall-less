package MouseDesktopChange;

import javax.swing.*;
import java.net.DatagramSocket;

public class MouseClicks implements Runnable {
    static private JFrame jFrame;
    private int val = 0;
    private SharedData sharedData;
    DatagramSocket datagramSocket;
    MouseFunctionality mouseFunctionality;

    public MouseClicks(JFrame jFrame, SharedData sharedData, DatagramSocket datagramSocket) {
        MouseClicks.jFrame = jFrame;
        this.sharedData = sharedData;
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {

        while (true){
            if ((sharedData.getForMouseClicks() == 0) && (val == 0)){
                System.out.println("Calling Mouse Functionality");
                SwingUtilities.invokeLater(() -> {
                    mouseFunctionality = new MouseFunctionality(jFrame);
                });
                val ++;
            } else if ((sharedData.getForMouseClicks() == 1) && (val == 1)){
                System.out.println("Disposing Mouse Functionality");
                if (mouseFunctionality != null) {
                    mouseFunctionality.disposeMouseListener();
                }
                jFrame.dispose();
                val --;
            }
        }
    }
}
