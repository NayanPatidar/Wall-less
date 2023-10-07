package ServerLinux;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    static JFrame jFrame = new JFrame();
    JTextField jTextField = new JTextField();

    DatagramSocket datagramSocket;
    String clientScreenSize;
    int portUDP = 12345;
    InetAddress inetAddress;
    String msgFromClient = "";
    String side = "Left";

    {
        try {
            inetAddress = InetAddress.getByName("10.200.233.31");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    public Main () {
        System.out.println("----Starting Program----");

        // Creating a JFrame
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setAlwaysOnTop(true);
        jFrame.setBackground(new Color(0,0,0,3));
        jFrame.setType(Window.Type.UTILITY);

        UDPConnectionValidation();
        System.out.println("Program Started");
        GUIAndMouse();
        System.out.println("ENDED");

    }

    private void GUIAndMouse() {
    }

    private void UDPConnectionValidation() {
    }
}
