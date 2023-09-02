package MouseDesktopChange;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GUI implements Runnable {
    DatagramSocket datagramSocket;
    ServerSocket serverSocket;
    Socket socket;
    JFrame jFrame;
    SharedData sharedData;
    InetAddress inetAddress;
    int port ;
    int portTCP;

    public GUI(JFrame jFrame, SharedData sharedData, Socket socket, InetAddress inetAddress, DatagramSocket datagramSocket, ServerSocket serverSocket, int portTCP, int port) {
        this.socket = socket;
        this.sharedData = sharedData;
        this.jFrame = jFrame;
        this.inetAddress = inetAddress;
        this.datagramSocket = datagramSocket;
        this.portTCP = portTCP;
        this.port = port;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        System.out.println("Got into run of GUI");
        while (true){
            Point mousePosition = MouseInfo.getPointerInfo().getLocation();
            // If the mouse position is less than 80 then starting the share coordinates
            // and bringing the transparent frame on the pc

            if (mousePosition.getX() < 80 && (sharedData.getForGui() == 1)){
                System.out.println("Leaving Screen");
                jFrame.setVisible(true);
                sharedData.setForGui(0);
                new CoordinatesSending(socket, datagramSocket, inetAddress, port, portTCP);

            } else if (mousePosition.getX() >= 80 && (sharedData.getForGui() == 0)) {
                System.out.println("Entered Screen");
                jFrame.dispose();
                sharedData.setForGui(1);
            }
        }
    }
}
