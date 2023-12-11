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
    Socket socket;
    String clientScreenSize;

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
        jFrame.setAlwaysOnTop(false);


        UDPConnectionValidation();
        TCPConnectionValidation();

        if (connectionEstablished){
            System.out.println("Threads Started");
            GUIAndMouse();
        }
        System.out.println("ENDED");

    }
    private void UDPConnectionValidation() {
        try {
            datagramSocket = new DatagramSocket(port);
            inetAddress = InetAddress.getByName("10.200.233.131");
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
                String[] clientMsg = receivedMsg.split("  ");
                System.out.println(clientMsg[0]);
                clientScreenSize = clientMsg[1];
                if (clientMsg[0].equals("Got it")){
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
            socket = serverSocket.accept();
            System.out.println("Client connected :" + socket.getInetAddress());

            String sendingUDPMsg = "StartingTCP";
            OutputStream outputStream = socket.getOutputStream();
            byte[] sendingMsg = sendingUDPMsg.getBytes();
            outputStream.write(sendingMsg);

            InputStream inputStream = socket.getInputStream();
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

        Thread threadA = new Thread(new GUI(jFrame, sharedData, socket, inetAddress, datagramSocket, serverSocket, portTCP, port, clientScreenSize));
        Thread threadB = new Thread(new MouseClicks(jFrame , sharedData, socket) );

        threadA.start();
        threadB.start();

        try {
            threadA.join();
//            threadB.join();
            System.out.println("Threads closed");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
