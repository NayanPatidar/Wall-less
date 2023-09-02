package MouseDesktopChange;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.Socket;

public class MouseClicks implements Runnable {
    static private JFrame jFrame;
    private int val = 0;
    private SharedData sharedData;
    Socket socket;
    MouseFunctionality mouseFunctionality;

    public MouseClicks(JFrame jFrame, SharedData sharedData, Socket socket) {
        MouseClicks.jFrame = jFrame;
        this.sharedData = sharedData;
        this.socket = socket;
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
