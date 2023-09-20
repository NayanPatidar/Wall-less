package Server;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class EventCaller implements Runnable{
    static private JWindow jWindow;
    private int val = 0;
    private final SharedData sharedData;
    DatagramSocket datagramSocket;
    EventListener eventListener;
    InetAddress inetAddress;
    int portUDP;
    JPanel jPanel;

    public EventCaller(JPanel jPanel, JWindow jWindow, SharedData sharedData, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
        EventCaller.jWindow = jWindow;
        this.sharedData = sharedData;
        this.datagramSocket = datagramSocket;
        this.portUDP = portUDP;
        this.inetAddress = inetAddress;
        this.jPanel = jPanel;
    }

    @Override
    public void run() {
        while (true){
            if ((sharedData.getForButtonClicks() == 0) && (val == 0)){
                System.out.println("Calling Keyboard Functionality");

                SwingUtilities.invokeLater(() -> {
                    eventListener = new EventListener(jPanel,jWindow,datagramSocket,inetAddress,portUDP);
                });
                val ++;
            } else if ((sharedData.getForButtonClicks() == 1) && (val == 1)){
                if (eventListener != null) {
                    System.out.println("Disposing Keyboard Functionality");
                    eventListener.removeEventListeners();
                }
                jWindow.dispose();
                val --;
            }
        }
    }
}
