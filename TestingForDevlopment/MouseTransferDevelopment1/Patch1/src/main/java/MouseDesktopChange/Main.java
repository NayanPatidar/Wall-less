package MouseDesktopChange;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;

public class Main {

    static JFrame jFrame = new JFrame();
    DatagramSocket datagramSocketForSending;
    DatagramSocket datagramSocketForReceiving;

    int senderPort = 12345;
    int receivingPort = 12346;
    InetAddress inetAddress;
    public Main(){

        // Creating a JFrame

        final Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setOpacity(0.05f);
        jFrame.setAlwaysOnTop(true);
//        creatingThreads(jFrame);

        try {

            // First making the connection for Sending the Packets
            String message = "Starting";
            byte[] sendData = message.getBytes();
            inetAddress = InetAddress.getByName("ok");
            datagramSocketForSending = new DatagramSocket(senderPort);

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, senderPort);
            datagramSocketForSending.send(sendPacket);

            // Second making the connection for Receiving the Packets
            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocketForReceiving.receive(receivePacket);

            String receivedMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if (receivedMsg.equals("Got it")){
                GUIAndMouse(datagramSocketForSending, datagramSocketForReceiving);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void GUIAndMouse(DatagramSocket datagramSocketForSending, DatagramSocket datagramSocketForReceiving) {
    }

    public void GUIAndMouse(){
        SharedData sharedData = new SharedData();

        Thread threadA = new Thread(new GUI(jFrame, sharedData,datagramSocketForSending, datagramSocketForReceiving));
        Thread threadB = new Thread(new MouseClicks(jFrame , sharedData,datagramSocketForSending, datagramSocketForReceiving) );

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
            System.out.println("Here");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
