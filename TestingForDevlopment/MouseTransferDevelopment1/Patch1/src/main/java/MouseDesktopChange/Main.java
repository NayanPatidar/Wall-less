package MouseDesktopChange;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;

public class Main {

    static JFrame jFrame = new JFrame();
    DatagramSocket datagramSocket;

    int port = 12345;

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

        UDPConnectionValidation();
        TCPConnectionValidation();

    }
    private void UDPConnectionValidation() {
        try {
            datagramSocket = new DatagramSocket(port);
            inetAddress = InetAddress.getByName("10.200.233.99");
            byte[] receiveData = new byte[1024];

            System.out.println("Server started on port 12345");

            while (true) {
                // Sending the Message First
                String MessageToSend = "Starting";
                byte[] sendData = MessageToSend.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(sendData, sendData.length, inetAddress, 12345);
                datagramSocket.send(datagramPacket);
                System.out.println("Sent");

                // Receiving the response from Client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);

                String receivedMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (receivedMsg.equals("Got it")){
                    System.out.println("Got the Message from Client: " + receivedMsg);
                    break;
                }
            }
        } catch (IOException e) {
	        throw new RuntimeException(e);
        }
    }

    private void TCPConnectionValidation() {

    }

    public void GUIAndMouse(){
        SharedData sharedData = new SharedData();

        Thread threadA = new Thread(new GUI(jFrame, sharedData, datagramSocket ));
        Thread threadB = new Thread(new MouseClicks(jFrame , sharedData, datagramSocket) );

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
