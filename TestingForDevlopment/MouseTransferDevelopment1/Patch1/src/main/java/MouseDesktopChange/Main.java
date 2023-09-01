package MouseDesktopChange;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Main {

    static JFrame jFrame = new JFrame();

    DatagramSocket datagramSocket;
    ServerSocket serverSocket;

    int port = 12345;
    int portTCP = 12346;
    boolean connectionEstablished = false;

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


        UDPConnectionValidation();
        TCPConnectionValidation();

        if (connectionEstablished){
            System.out.println("Threads Started");
//            GUIAndMouse();
        }

    }
    private void UDPConnectionValidation() {
        try {
            datagramSocket = new DatagramSocket(port);
            inetAddress = InetAddress.getByName("10.200.233.99");
            byte[] receiveData = new byte[1024];

            System.out.println("Server started on port 12345");

            while (true) {
                // Sending the Message First
                String MessageToSend = "StartingUDP";
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
        try {
            serverSocket = new ServerSocket(portTCP);
            System.out.println("TCP server is listening on port " + portTCP);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected :" + clientSocket.getInetAddress());

            String sendingUDPMsg = "StartingTCP";
            OutputStream outputStream = clientSocket.getOutputStream();
            byte[] sendingMsg = sendingUDPMsg.getBytes();
            outputStream.write(sendingMsg);

            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String clientMessage = new String(buffer, 0, bytesRead);
            System.out.println("Received from client: " + clientMessage);
            connectionEstablished = true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
