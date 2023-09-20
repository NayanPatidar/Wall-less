package Server;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class EventCaller implements Runnable{
    static private JWindow jWindow;
    private int val = 0;
    private final SharedData sharedData;
    Socket socket;
    DatagramSocket datagramSocket;
    EventListener eventListener;
    InetAddress inetAddress;
    int portUDP;
    public EventCaller(JWindow jWindow, SharedData sharedData, Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
        EventCaller.jWindow = jWindow;
        this.sharedData = sharedData;
        this.socket = socket;
        this.datagramSocket = datagramSocket;
        this.portUDP = portUDP;
        this.inetAddress = inetAddress;
    }

    @Override
    public void run() {
        while (true){
            if ((sharedData.getForButtonClicks() == 0) && (val == 0)){
                System.out.println("Calling Keyboard Functionality");

                SwingUtilities.invokeLater(() -> {
                    eventListener = new EventListener(jWindow,datagramSocket, inetAddress, portUDP);
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
