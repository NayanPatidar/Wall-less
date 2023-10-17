package ServerLinux;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Objects;

public class GUI {
    private JFrame jFrame;
    private DatagramSocket datagramSocket;
    private int portUDP;
    private String clientScreenSize;
    private ArrayList<Clients> clientsArrayList;
    public static int loopNum = 0;
    EventListener eventListener;
    public int val = 0;
    public int currentIndex;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    public GUI(String side, JFrame jFrame, DatagramSocket datagramSocket, int portUDP, String clientScreenSize, ArrayList<Clients> clientsArrayList) {
        this.datagramSocket = datagramSocket;
        this.portUDP = portUDP;
        this.clientScreenSize = clientScreenSize;
        this.jFrame = jFrame;
        this.clientsArrayList = clientsArrayList;

        GUIFunctionality();
    }

    public GUI(){
        // Just to access and modify the loopNum
    }

    public void setLoopNum(int loopNum) {
        GUI.loopNum = loopNum;
    }

    public int getArrayListIndex(){
        return currentIndex;
    }

    private void GUIFunctionality() {
        System.out.println("Screen Sharing started !!");

        while (true) {
            Point cursor = MouseInfo.getPointerInfo().getLocation();
            // Basically if the clientArrayList has One size then
            // if it is located on the left side set the if condition to the following

            if (loopNum == 0 && cursor.getX() < 2 && (val == 0)&& Objects.equals(clientsArrayList.get(0).getSide(), "Left")){
                jFrame.setVisible(true);
                currentIndex = 0;
                System.out.println("Leaving Screen");
                System.out.println("Calling Keyboard Functionality");
                SwingUtilities.invokeLater(() -> {
                    eventListener = new EventListener(jFrame, datagramSocket, clientsArrayList.get(0).getInetAddress(), portUDP);
                });
                new CoordinatesSendingLeft(clientsArrayList.get(0).getSide(), datagramSocket, clientsArrayList.get(0).getInetAddress(), portUDP, clientsArrayList.get(0).getScreenSize());
                val++;

            }else if (loopNum == 0 && cursor.getX() > 2  && (val == 1) && Objects.equals(clientsArrayList.get(0).getSide(), "Left")) {
                System.out.println("Entering Screen");
                jFrame.dispose();
                eventListener.removeEventListeners();
                val--;
            }
            if (clientsArrayList.size() > 1) {
                if (loopNum == 0 && cursor.getX() > toolkit.getScreenSize().getWidth() - 2 && (val == 0) && Objects.equals(clientsArrayList.get(1).getSide(), "Right")) {
                    currentIndex = 1;
                    System.out.println(clientsArrayList.get(1).getInetAddress() + " " + clientsArrayList.get(1).getSide());
                    jFrame.setVisible(true);
                    System.out.println("Leaving Screen and Entering Right Screen");
                    System.out.println("Calling Keyboard Functionality");
                    SwingUtilities.invokeLater(() -> {
                        eventListener = new EventListener(jFrame, datagramSocket, clientsArrayList.get(1).getInetAddress(), portUDP);
                    });
                    new CoordinatesSendingRight(clientsArrayList.get(1).getSide(), datagramSocket, clientsArrayList.get(1).getInetAddress(), portUDP,  clientsArrayList.get(1).getScreenSize());
                    val++;

                } else if (loopNum == 0 && cursor.getX() <= toolkit.getScreenSize().getWidth() - 2 && (val == 1) && Objects.equals(clientsArrayList.get(1).getSide(), "Right")) {
                    System.out.println("Entering Screen");
                    jFrame.dispose();
                    eventListener.removeEventListeners();
                    val--;
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
