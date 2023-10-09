package ServerLinux;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public Main () {
        System.out.println("----Starting Program----");

        // Creating a JFrame

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
